package com.wsw.java.exercise.test01;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池，队列阻塞获取任务和加入任务示例
 *
 * @author loriyuhv
 * @version 1.0 2026/8/12 18:01
 * @since 1.0
 */
@Slf4j(topic = "c.TestPool")
public class TestPool01 {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 3);
        for (int i = 0; i < 3; i++) {
            int j = i;
            threadPool.execute(() -> log.debug("{}", j));
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
     * 线程集合
     */
    private final HashSet<Worker> workers = new HashSet<>();

    /**
     * 核心线程数
     */
    private final int coreSize;


    public ThreadPool(int coreSize, int queueCapacity) {
        this.coreSize = coreSize;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
    }

    /**
     * 执行任务
     * @param task 任务
     */
    public void execute(Runnable task) {
        // 当任务数没有超过 coreSize 时，直接交给Worker对象执行
        // 如果任务数超过 coreSize 时，加入任务队列暂存
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增 worker {}, {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                taskQueue.put(task);
            }
        }
    }

    /**
     * 线程
     */
    class Worker extends Thread {
        /**
         * 任务
         */
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 执行任务
            // 1）当task不为空，执行任务
            // 2）当task为空，从任务队列获取任务执行
            while(task != null || (task = taskQueue.take()) != null) {
                try {
                    log.debug("正在执行... {}", task);
                    task.run();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                log.debug("worker 被移除了{}", this);
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
     * 消费者条件变量
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
     * 阻塞获取任务
     */
    public T take() {
        lock.lock();

        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaiting.await();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            T t = queue.removeLast();
            log.debug("阻塞获取任务：{}", t);
            fullWaiting.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞添加任务
     */
    public void put(T t) {
        lock.lock();

        try {
            while(queue.size() == capacity) {
                try {
                    fullWaiting.await();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            queue.addFirst(t);
            log.debug("阻塞加入任务队列：{}", t);
            emptyWaiting.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取队列大小
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
