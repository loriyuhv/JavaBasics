package com.wsw.guarded_suspension.example02;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个线程等待另一个线程的结果
 * t1线程等待t2线程的结果
 * t1等待线程设置超时
 * @author loriyuhv
 * @version 1.0 2025/9/25 15:56
 * @since 1.0
 */
@Slf4j
public class GuardedSuspensionTimeoutTest {
    public static void main(String[] args) {
        TimeoutGuardedObject guardedObject = new TimeoutGuardedObject();
        Thread t1 = new Thread(() -> {
            Object response = guardedObject.getResponse(2000);
            if (response != null) {
                log.debug("t1获得了结果：{}", response);
            } else {
                log.debug("t1没有获得结果。");
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
            }
            guardedObject.setResponse(new Object());
            log.debug("t2设置了结果。");
        }, "t2");
        t2.start();
    }
}
