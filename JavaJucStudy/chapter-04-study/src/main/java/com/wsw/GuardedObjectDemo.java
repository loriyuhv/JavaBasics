package com.wsw;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/25 6:42
 * @description
 */
@Slf4j
public class GuardedObjectDemo {
    static final Object lock = new Object();
    static int number;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            synchronized (lock) {
                number += 1;
                lock.notify();
            }
        }, "t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    log.debug("number:{}", number);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }, "t2");
        t2.start();
    }
}
