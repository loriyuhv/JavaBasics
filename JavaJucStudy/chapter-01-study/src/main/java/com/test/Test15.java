package com.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 守护线程
 * @author loriyuhv
 * @version 1.0 2026/8/14 12:36
 * @since 1.0
 */
@Slf4j(topic = "c.Test15")
public class Test15 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.debug("t1 结束");
        }, "t1");
        t1.setDaemon(true);
        t1.start();
        Thread.sleep(1000);
        log.debug("main 结束");
    }
}
