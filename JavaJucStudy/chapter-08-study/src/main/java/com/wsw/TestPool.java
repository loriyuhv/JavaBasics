package com.wsw;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author loriyuhv
 * @version 1.0 2025/11/5 16:32
 * @since 1.0
 */
@Slf4j(topic = "c.TestPool")
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(
                1, 1000, TimeUnit.MILLISECONDS, 1,
                (queue, task) -> {
                    // 1. 死等
                    // queue.put(task);
                    // 2. 带超时等待
                    // queue.offer(task, 1500,  TimeUnit.MILLISECONDS);
                    // 3）放弃任务执行
                    // log.debug("放弃执行{}任务", task);
                    // 4）抛出异常
                    // throw new RuntimeException("任务执行失败，{}" + task);
                    // 5）让调用者自己执行任务
                    task.run();
                });

        for (int i = 0; i < 4; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                log.debug("{}", j);
            });
        }
    }
}

@FunctionalInterface // 拒绝策略
interface RejectPolicy<T> {

    void reject(BlockingQueue<T> queue, T task);

}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    /* 任务队列 */
    private final BlockingQueue<Runnable> taskQueue;

    /* 线程集合 */
    private final HashSet<Worker> workers = new HashSet<>();

    /* 核心线程数 */
    private final int coreSize;

    /* 获取任务的超时时间 */
    private final long timeout;

    private final TimeUnit timeUnit;

    /* 6. 拒绝策略 */
    private final RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(
            int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    /* 执行任务 */
    public void execute(Runnable task) {
        /* 当任务数没有超过coreSize时，直接交给worker对象执行 */
        /* 如果任务数超过coreSize时，加入任务队列暂存 */
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增worker{}, {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                // taskQueue.put(task);
                // 1）死等
                // 2）带超时时间的等待
                // 3）放弃任务执行
                // 4）抛出异常
                // 5）让调用者自己执行任务
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    class Worker extends Thread {
        private Runnable task;
        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            /* 执行任务 */
            /* 1）当task不为空，执行任务 */
            /* 2）当task执行完毕，再接着从任务队列获取任务执行 */
            // while (task != null || (task =  taskQueue.take()) != null) {
            while (true) {
                try {
                    if (!(task != null || (task =  taskQueue.poll(timeout, timeUnit)) != null)) break;
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
                try {
                    log.debug("正在执行...{}", task);
                    task.run();
                } catch (Exception e) {
                    log.info(e.getMessage());
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                log.debug("worker被移除{}", this);
                workers.remove(this);
            }
        }
    }
}

@Slf4j(topic = "c.BlockingQueue")
class BlockingQueue<T> {
    /**
     * 任务队列
     */
    private final Deque<T> queue = new ArrayDeque<>();

    /**
     * 锁
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 生产者条件变量
     */
    private final Condition fullWaitSet = lock.newCondition();

    /**
     * 消费者条件变量
     */
    private final Condition emptyWaitSet = lock.newCondition();

    /**
     * 容量
     */
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();

        try {
            // 判断队列是否已满
            if (queue.size() == capacity) {
                rejectPolicy.reject(this, task);
            } else { // 有空闲
                log.debug("blocking queue: 加入任务队列 {}", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    /* 带超时的阻塞获取 */
    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        lock.lock();
        try {
            /* 将timeout统一转换为纳秒 */
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    /* 返回值：剩余时间 */
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /* 阻塞获取 */
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 带超时时间的阻塞添加
     * @return 添加结果
     */
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();

        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    if (nanos < 0) {
                        return false;
                    }
                    log.debug("等待加入任务队列 {}", task);
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }

            log.debug("加入任务队列 {}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }

        return true;
    }

    /* 阻塞添加 */
    public void put(T element) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    log.debug("等待加入任务队列 {} ...", element);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            queue.addLast(element);
            log.debug("加入任务队列: {}", element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    /* 获取大小 */
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
