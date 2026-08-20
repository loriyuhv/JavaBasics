package com.wsw.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/20 09:03
 * @since 1.0
 */
@Slf4j(topic = "c.TestExecutors")
public class TestExecutors {
    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(() -> {
            log.debug("1");
            int i = 1 / 0;
            log.debug("{}", i);
        });

        pool.execute(() -> log.debug("2"));
        pool.execute(() -> log.debug("3"));
    }
}
