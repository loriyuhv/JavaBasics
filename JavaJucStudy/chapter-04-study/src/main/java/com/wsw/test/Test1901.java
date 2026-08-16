package com.wsw.test;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>sleep(long n) wait(long n)的区别</p>
 * <li>1) sleep是Thread方法，而wait是Object方法</li>
 * <li>2) sleep不需要强制和synchronized配合使用，但wait需要</li>
 * <li>3) sleep在睡眠的同时，不会释放对象锁，但wait在等待的时候会释放对象锁</li>
 * <li>4）它们的状态都是TIMED_WAITING</li>
 *
 * @author loriyuhv
 * @version 1.0 2025/9/24 17:36
 */
@Slf4j
public class Test1901 {
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                log.debug("t1获得锁了");
                try {
                    Thread.sleep(20000); // 主线程获取不了锁lock
                    // lock.wait(20000); // 主线程可以获取锁lock
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }, "t1");
        t1.start();

        Thread.sleep(1000);
        log.debug("t1 state: {}", t1.getState());
        synchronized (lock) {
            log.debug("main获得锁了");
        }
    }
}
