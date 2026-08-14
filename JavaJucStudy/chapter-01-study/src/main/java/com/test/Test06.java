package com.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 调用sleep会让当前线程从Running状态进入Timed Waiting状态
 * @author loriyuhv
 * @version 1.0 2025/9/21 11:53
 */
@Slf4j(topic = "c.Test06")
public class Test06 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
                log.debug("running ...");
            }
        };
        log.debug("before start:{}", t1.getState()); // NEW
        t1.start();
        log.debug("after start:{}", t1.getState()); // RUNNABLE ?:主线程执行的时候，t1线程还没执行到sleep
        Thread.sleep(1000);
        log.debug("after start:{}", t1.getState()); // TIMED_WAITING
    }
}
