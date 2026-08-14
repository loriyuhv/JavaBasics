package com.test;

import lombok.extern.slf4j.Slf4j;

/**
 * interrupt 打断sleep、wait、join方法 线程的阻塞状态
 *
 * @author loriyuhv
 * @version 1.0 2025/9/21 13:19
 */
@Slf4j(topic = "c.Test11")
public class Test11 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleep ...");
            try {
                Thread.sleep(5000); // sleep,wait,join，interrupt()后会把打断标记清空，抛出异常
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            // log.debug("t1 running");
        }, "t1");

        t1.start();
        Thread.sleep(500);
        log.debug("t1 state: {}", t1.getState());
        Thread.sleep(500);
        log.debug("interrupt");
        t1.interrupt();
        Thread.sleep(500);
        log.debug("t1 state: {}", t1.getState());
        log.debug("打断标记：{}", t1.isInterrupted());
    }
}
