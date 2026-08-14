package com.wsw98;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * interrupt 对park()的影响
 *
 * @author loriyuhv
 * @version 1.0 2025/9/21 14:56
 */
@Slf4j
public class Test14 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park ...");
            LockSupport.park();
            log.debug("unpark...");
            // log.debug("打断状态：{}", Thread.currentThread().isInterrupted()); // 判断打断状态，不会清楚打断标记
            log.debug("打断状态：{}", Thread.interrupted()); // 判断打断状态，会清楚打断标记

            LockSupport.park(); // interrupt之后，再park()不起作用。原因：因为打断标记为true
            log.debug("unpark ...");
        }, "t1");

        t1.start();

        Thread.sleep(1000);
        log.debug("t1 state: {}", t1.getState());
        t1.interrupt();
        Thread.sleep(500);
        log.debug("t1 state: {}", t1.getState());
    }
}
