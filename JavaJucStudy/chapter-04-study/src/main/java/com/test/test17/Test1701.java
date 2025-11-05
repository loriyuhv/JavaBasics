package com.test.test17;

import lombok.extern.slf4j.Slf4j;

/**
 * 优化Test1700
 *
 * @author loriyuhv
 * @version 1.0 2025/11/5 20:16
 * @since 1.0
 */
@Slf4j
public class Test1701 {
    static int counter = 0;
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5000; i++) {
                    counter++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5000; i++) {
                    counter--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("counter = {}", counter);
    }
}
