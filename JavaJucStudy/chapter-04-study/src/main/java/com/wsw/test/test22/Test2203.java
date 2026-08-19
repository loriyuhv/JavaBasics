package com.wsw.test.test22;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * 超时
 * @author loriyuhv
 * @version 1.0 2026/8/19 13:09
 * @since 1.0
 */
@Slf4j(topic = "c.Test2203")
public class Test2203 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            // 尝试获取锁
            log.debug("尝试获取锁");
            // if (!lock.tryLock()) {
            try {
                if (!lock.tryLock(2, TimeUnit.SECONDS)) {
                    log.debug("获取不到锁");
                    return;
                }
            } catch (InterruptedException e) {
                log.debug("获取不到锁 ;");
                log.error(e.getMessage(), e);
                return;
            }

            try {
                log.debug("获取到锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.debug("获取到锁");
        t1.start();
        Sleeper.sleep(1000);
        lock.unlock(); // 主线程释放锁
        // t1.interrupt();
    }
}
