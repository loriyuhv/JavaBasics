package com.wsw.java;

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
@Slf4j
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 10);
        for (int i = 0; i < 5; i++) {
            int j = i;
            threadPool.execute(() -> {
                log.debug("{}", j);
            });
        }
    }
}

@Slf4j
class ThreadPool {
    /* 任务队列 */
    private BlockingQueue<Runnable> taskQueue;

    /* 线程集合 */
    private HashSet<Worker> workers = new HashSet<>();

    /* 核心线程数 */
    private int coreSize;

    /* 获取任务的超时时间 */
    private long timeout;

    private TimeUnit timeUnit;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
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
                log.debug("加入任务队列{}", task);
                taskQueue.put(task);
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

@Slf4j
class BlockingQueue<T> {
    /* 1. 任务队列 */
    private Deque<T> queue = new ArrayDeque<>();

    /* 2. 锁 */
    private ReentrantLock lock = new ReentrantLock();

    /* 3. 生产者条件变量 */
    private Condition fullWaitSet = lock.newCondition();

    /* 4. 消费者条件变量 */
    private Condition emptyWaitSet = lock.newCondition();

    /* 5. 容量 */
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /* 带超时的阻塞获取 */
    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        lock.lock();
        try {
            /* 将timout统一转换为纳秒 */
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

    /* 阻塞添加 */
    public void put(T element) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            queue.addLast(element);
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
