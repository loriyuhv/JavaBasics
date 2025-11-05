package com.test.test17;

import lombok.extern.slf4j.Slf4j;

/**
 * 参考课程Test17代码
 *
 * @author loriyuhv
 * @version 1.0 2025/11/5 20:05
 * @since 1.0
 */
@Slf4j
public class Test1700 {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter--;
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("counter = {}", counter);
    }
}
