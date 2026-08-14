package com.wsw.exercise.test04;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/14 09:39
 * @since 1.0
 */
@Slf4j(topic = "c.TestThreadPool")
public class TestThreadPool {
    public static void main(String[] args) {
        ThreadPool threads = new ThreadPool(
                2,
                1000L,
                TimeUnit.MILLISECONDS,
                2,
                // (queue, task) -> {
                //     // 1. 死等
                //     // queue.put(task);
                //     // 2. 带超时的等待
                //     queue.offer(task, 500, TimeUnit.MILLISECONDS);
                //
                // }
                // (queue, task) -> queue.put(task);
                BlockingQueue::put
                );
        for (int i = 0; i < 7; i++) {
            int j = i;
            threads.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage(), e);
                }
                log.debug("{}", j);
            });
        }
    }
}

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    private final BlockingQueue<Runnable> tasks;
    private final HashSet<Worker> workers = new HashSet<>();
    private final int coreSize;
    private final long timeout;
    private final TimeUnit unit;
    private final RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit unit, int queueCapacity,  RejectPolicy<Runnable> rejectPolicy) {
        this.tasks = new BlockingQueue<>(queueCapacity);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("create worker {} {}", worker, task);
                workers.add(worker);
                worker.start();
                return;
            }

            // tasks.put(task);
            // tasks.offer(task, timeout, unit);
            tasks.tryPut(rejectPolicy, task);
        }
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // while (task != null || (task = tasks.take()) != null) {
            while (task != null || (task = tasks.poll(timeout, unit)) != null) {
                try {
                    log.debug("run task {}", task);
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
    private final Condition fullWaiting = lock.newCondition();
    private final Condition emptyWaiting = lock.newCondition();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if (queue.size() < capacity) {
                log.debug("put task {}", task);
                queue.addLast(task);
                return;
            }
        } finally {
            lock.unlock();
        }
        rejectPolicy.reject(this, task);
    }
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

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
            log.debug("offer {}", t);
            emptyWaiting.signal();
        } finally {
            lock.unlock();
        }
    }

    public void put(T t) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    fullWaiting.await();
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

    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                try {
                    nanos = emptyWaiting.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            T t = queue.removeFirst();
            log.debug("poll {}", t);
            fullWaiting.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

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
            T t = queue.removeFirst();
            log.debug("take {}", t);
            fullWaiting.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }
}
