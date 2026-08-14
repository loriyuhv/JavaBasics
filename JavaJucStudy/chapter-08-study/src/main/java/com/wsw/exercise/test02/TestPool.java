package com.wsw.exercise.test02;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定一线程池测试入口
 * 演示简易线程池：核心线程 + 有界阻塞队列 + 核心线程空闲超时销毁模型
 * @author loriyuhv
 * @version 1.0 2026/8/13 10:04
 * @since 1.0
 */
@Slf4j(topic = "c.TestPool")
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000L, TimeUnit.MILLISECONDS, 2);

        for (int i = 0; i < 4; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    log.debug(e.getMessage(), e);
                }
                log.debug("{}", j);
            });
        }
    }
}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    /**
     * 任务队列
     */
    private final BlockingQueue<Runnable> taskQueue;

    /**
     * 线程池
     */
    private final HashSet<Worker> workers = new HashSet<>();

    /**
     * 核心线程数
     */
    private final int coreSize;

    /**
     * 获取任务的超时时间
     */
    private final long timeout;

    /**
     * 时间单位
     */
    private final TimeUnit timeUnit;


    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity) {
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增线程 {}，处理任务 {}", worker, task);
                workers.add(worker);
                worker.start();
                return;
            }

            taskQueue.offer(task, timeout, timeUnit);
        }
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    log.debug("正在执行...{}", task);
                    task.run();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
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
    private final Condition fullWaiting = lock.newCondition();

    /**
     * 消费者
     */
    private final Condition emptyWaiting = lock.newCondition();


    /**
     * 容量
     */
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 带超时时间加入任务
     */
    public void offer(T t, long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    if (nanos <= 0) {
                        return;
                    }
                    nanos = fullWaiting.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            queue.addLast(t);
            log.debug("put {}", t);
            emptyWaiting.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 带超时时间获取任务
     */
    public T poll(long timeout, TimeUnit timeUnit) {
        lock.lock();

        try {
            long nanos = timeUnit.toNanos(timeout);

            while (queue.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaiting.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            T t = queue.removeFirst();
            log.debug("take {}", t);
            fullWaiting.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 任务队列大小
     */
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
