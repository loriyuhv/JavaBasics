package com.wsw.test.test26;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步模式之顺序控制
 * 固定运行顺序
 * 例子：必须先2后1打印
 * await signal解法
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 14:32
 * @since 1.0
 */
@Slf4j(topic = "c.Test2501")
public class Test2601 {
    /**
     * 锁
     */
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    /**
     * t2线程是否运行的标志
     * 避免虚假唤醒
     */
    private static boolean flag = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                while (!flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            } finally {
                lock.unlock();
            }
            log.debug("1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                flag = true;
                log.debug("2");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
