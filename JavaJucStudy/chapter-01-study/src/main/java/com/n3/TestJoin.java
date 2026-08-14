package com.n3;

import lombok.extern.slf4j.Slf4j;

/**
 * join()
 * @author loriyuhv
 * @version 1.0 2025/9/21 13:04
 */
@Slf4j(topic = "c.TestJoin")
public class TestJoin {
    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
        TestJoin.test2();
        log.debug("==========================");
        TestJoin.test3();
    }

    public static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            sleep(1000);
            r1 = 10;
        }, "t1");

        Thread t2 = new Thread(() -> {
            sleep(2000);
            r2 = 20;
        }, "t2");

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        log.debug("join begin");
        t1.join();
        log.debug("t1 join end");
        t2.join();
        log.debug("t2 join end");
        long end = System.currentTimeMillis();
        log.debug("r1: {} r2: {} cost: {}", r1, r2, end - start);
    }

    public static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            sleep(2000);
            r1 = 10;
        }, "t1");

        long start = System.currentTimeMillis();
        t1.start();
        // 线程执行结束会导致join结束
        log.debug(" join begin");
        // t1.join(1500); // 1500ms
        t1.join(3000); // 2000ms
        long end = System.currentTimeMillis();
        log.debug(" r1: {} r2: {} cost: {}", r1, r2, end - start);
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
