package com.wsw02.n4;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/24 17:15
 * @description
 */
@Slf4j
public class TestWaitNotify {
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (lock) {
                log.debug("t1，执行...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
                log.debug("t1，其他代码...");
            }
        }, "t1").start();

        new Thread(()->{
            synchronized (lock) {
                log.debug("t2，执行...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
                log.debug("t2，其他代码...");
            }
        }, "t2").start();

        Thread.sleep(2000);
        log.debug("main，唤醒其他线程...");
        synchronized (lock) {
            // lock.notify(); // 唤醒lock上的一个线程
            lock.notifyAll(); // 唤醒lock上的所有线程
        }
    }
}
