package com.wsw.exercise.test03;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/13 21:30
 * @since 1.0
 */
@Slf4j(topic = "c.TestPool")
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(
                1,
                1,
                BlockingQueue::put
        );
        for (int i = 0; i < 100; i++) {
            int j = i;
            threadPool.execute(() -> log.debug("{}", j));
        }
    }
}

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final HashSet<Worker> workers = new HashSet<>();
    private final int coreSize;
    private final RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("new worker {} {}", worker, task);
                workers.add(worker);
                worker.start();
                return;
            }

            // taskQueue.put(task);
            taskQueue.tryPut(task, rejectPolicy);
        }
    }


    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task = taskQueue.take()) != null) {
                try {
                    log.debug("task execute {}", task);
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
    private final Deque<T> deque = new ArrayDeque<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition fullWaiting = lock.newCondition();
    private final Condition emptyWaiting = lock.newCondition();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public T take() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                try {
                    emptyWaiting.await();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            T t = deque.removeFirst();
            log.debug("consumer has taken {}", t);
            fullWaiting.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public void put(T t) {
        lock.lock();
        try {
            while (deque.size() == capacity) {
                try {
                    fullWaiting.await();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }

            deque.addLast(t);
            log.debug("producer has put {}", t);
            emptyWaiting.signal();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(T t, RejectPolicy<T> rejectPolicy) {
        lock.lock();

        try {
            if (deque.size() < capacity) {
                deque.addLast(t);
                emptyWaiting.signal();
                return;
            }
        } finally {
            lock.unlock();
        }

        rejectPolicy.reject(this, t);
    }

    public int size() {
        lock.lock();
        try {
            return deque.size();
        } finally {
            lock.unlock();
        }
    }
}
