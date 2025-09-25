package com.wsw.guarded_suspension.example01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/25 15:56
 * @description 一个线程等待另一个线程的结果
 * t1线程等待t2线程的结果
 */
@Slf4j
public class GuardedSuspensionTest {
    public static void main(String[] args) throws InterruptedException {
        GuardedObject guardedObject = new GuardedObject();
        Thread t1 = new Thread(() -> {
            Object response = guardedObject.getResponse();
            if (response != null) {
                log.debug("t1获得了结果：{}", response);
            } else {
                log.debug("t1没有获得结果。");
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
            }
            guardedObject.setResponse(new Object());
            log.debug("t2设置了结果。");
        }, "t2");
        t2.start();

        Thread t3 = new Thread(guardedObject::wakeup, "t3");
        t3.start();
    }
}
