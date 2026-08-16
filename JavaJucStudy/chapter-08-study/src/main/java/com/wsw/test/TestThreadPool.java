package com.wsw.test;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/15 21:19
 * @since 1.0
 */
@Slf4j(topic = "c.TestThreadPool")
public class TestThreadPool {
    public static void main(String[] args) {
        // 拒绝策略实现一
        ThreadPool threadPool = new ThreadPool(
                2,
                1000L,
                TimeUnit.MILLISECONDS,
                2,
                (queue, task) -> {
                    try {
                        queue.offer(task, 1000L, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
        );

        for (int i = 0; i < 5; i++) {
            int j = i;
            threadPool.execute(() -> log.debug("{}", j));
        }


        // 拒绝策略实现二
        ThreadPool threadPool1 = new ThreadPool(
                2,
                500L,
                TimeUnit.MILLISECONDS,
                2,
                (queue, task) -> {
                    try {
                        queue.put(task);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
        );

        for (int i = 5; i < 10; i++) {
            int j = i;
            threadPool1.execute(() -> log.debug("{}", j));
        }
    }

}

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T t);
}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final HashSet<Worker> workers = new HashSet<>();
    private final int coreSize;
    private final long timeout;
    private final TimeUnit unit;
    private final RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit unit, int queueCapacity,  RejectPolicy<Runnable> rejectPolicy) {
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
                return;
            }
            // taskQueue.offer(task, timeout, unit);
            taskQueue.tryPut(rejectPolicy, task);
        }
    }

    class Worker extends Thread {
        Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // if (!(task != null || (task = taskQueue.take()) != null)) break;
                    if (!(task != null || (task = taskQueue.poll(timeout, unit)) != null)) break;
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                try {
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
    private final Deque<T> queue = new ArrayDeque<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                full.await();
            }
            log.debug("put {}", t);
            queue.addLast(t);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void offer(T t, long timeout, TimeUnit unit) throws InterruptedException {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.size() == capacity) {
                if (nanos <= 0) {
                    return;
                }
                nanos = full.awaitNanos(nanos);
            }
            log.debug("offer {}", t);
            queue.addLast(t);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                empty.await();
            }
            T t = queue.removeFirst();
            log.debug("take {}", t);
            full.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                nanos = empty.awaitNanos(nanos);
            }
            T t = queue.removeFirst();
            log.debug("poll {}", t);
            full.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T t) {
        lock.lock();
        try {
            if (queue.size() < capacity) {
                queue.addLast(t);
                empty.signal();
                return;
            }
        } finally {
            lock.unlock();
        }
        rejectPolicy.reject(this, t);
    }
}
