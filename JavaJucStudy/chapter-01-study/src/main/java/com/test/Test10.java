package com.test;

import lombok.extern.slf4j.Slf4j;

/**
 * join使用: 等待线程结束
 * @author loriyuhv
 * @version 1.0 2025/9/21 12:49
 */
@Slf4j(topic = "c.Test10")
public class Test10 {
    public static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        Test10.test1();
    }

    public static void test1() throws InterruptedException {
        log.debug("main开始");
        Thread t1 = new Thread(() -> {
            log.debug("t1开始");
            sleep(1);
            log.debug("t1结束");
            r = 10;
        }, "t1");
        t1.start();
        t1.join();
        log.debug("结果为:{}", r);
        log.debug("main结束");
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
