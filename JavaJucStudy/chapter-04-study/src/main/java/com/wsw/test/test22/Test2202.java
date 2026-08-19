package com.wsw.test.test22;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * 支持可打断锁 lockInterruptibly
 * @author loriyuhv
 * @version 1.0 2026/8/19 13:00
 * @since 1.0
 */
@Slf4j(topic = "c.Test2202")
public class Test2202 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                // 如果没有竞争，那么此方法就会获取lock对象的锁
                // 如果有竞争，就会进入阻塞队列，可以被其他线程用interrupt方法打断
                log.debug("尝试获取锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
                log.debug("没有获取到锁，返回");
                return;
            }

            try {
                log.debug("获取到锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock(); // 测试t1线程进入阻塞队列
        t1.start();

        Sleeper.sleep(500);

        log.debug("打断t1");
        t1.interrupt();
    }
}
