package com.wsw04.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/24 17:36
 * @description sleep(long timeout) 和 wait(long timeout)区别
 * 1）sleep是Thread方法。wait是Object方法。
 * 2）sleep()不需要强制和synchronized关键字配合使用，但wait()需要。
 * 3）sleep()在睡眠时，不会释放对象锁，但wait()在等待时，会释放对象锁。
 * 4）它们的状态都是TIMED_WAITING
 */
@Slf4j
public class Test19 {
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                log.debug("t1获得锁了");
                try {
                    // Thread.sleep(20000);
                    lock.wait(20000);
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
