package com.wsw.n4.wait_notify;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/16 14:48
 * @since 1.0
 */
@Slf4j(topic = "c.TestWaitNotify02")
public class TestWaitNotify02 {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                log.debug("t1执行...");
                try {
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    log.debug(e.getMessage(), e);
                }
                log.debug("t1执行其他代码...");
            }
        }, "t1");
        t1.start();

        // Thread.sleep(500);
        Thread.sleep(2000);
        log.debug("唤醒lock上的其他线程");
        synchronized (lock) {
            lock.notify();
        }
    }
}
