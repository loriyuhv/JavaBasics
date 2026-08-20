package com.wsw.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 20:25
 * @since 1.0
 */
@Slf4j(topic = "c.TestThreadPoolExecutors")
public class TestThreadPoolExecutors {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3, new ThreadFactory() {
            private final AtomicInteger t = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {

                return new Thread(r, "my-pool-thread-" + t.getAndIncrement());
            }
        });

        pool.execute(() -> log.debug("1"));
        pool.execute(() -> log.debug("2"));
        pool.execute(() -> log.debug("3"));
    }
}
