package com.wsw.n8;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/20 09:39
 * @since 1.0
 */
@Slf4j(topic = "c.TestShutdown")
public class TestShutdown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<Integer> result1 = pool.submit(() -> {
            log.debug("task 1 running ...");
            Sleeper.sleep(1000);
            log.debug("task 1 finish ...");
            return 1;
        });

        Future<Integer> result2 = pool.submit(() -> {
            log.debug("task 2 running ...");
            Sleeper.sleep(1000);
            log.debug("task 2 finish ...");
            return 2;
        });

        Future<Integer> result3 = pool.submit(() -> {
            log.debug("task 3 running ...");
            Sleeper.sleep(1000);
            log.debug("task 3 finish ...");
            return 3;
        });

        log.debug("shutdown");
        // pool.shutdown();
        // boolean flag = pool.awaitTermination(3, TimeUnit.SECONDS);

        pool.shutdownNow();
        log.debug("other");
        // Future<Integer> result4 = pool.submit(() -> {
        //     log.debug("task 4 running ...");
        //     Sleeper.sleep(1000);
        //     log.debug("task 4 finish ...");
        //     return 4;
        // });
    }
}
