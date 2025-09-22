package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/22 12:17
 * @description
 */
@Slf4j
public class Test17 {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (Test17.class) {
            //     try {
            //         Thread.sleep(4000);
            //     } catch (InterruptedException e) {
            //         log.debug(e.getMessage());
            //     }
                for (int i = 0; i < 500000000; i++) {
                    counter++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (Test17.class) {
                for (int i = 0; i < 500000000; i++) {
                    counter--;
                }
            }
        }, "t2");

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();


        // Thread.sleep(1000);
        // log.debug("t1 state {}", t1.getState());
        // log.debug("t2 state {}", t2.getState());

        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        log.debug("total: {}", end - start); // 12ms
        log.debug("counter: {}", counter);
    }
}
