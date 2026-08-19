package com.wsw.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 同步模式之顺序控制
 * 固定运行顺序
 * 例子：必须先2后1打印
 * wait notify版本
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 14:32
 * @since 1.0
 */
@Slf4j(topic = "c.Test2501")
public class Test2501 {
    /**
     * 锁
     */
    private static final Object lock = new Object();

    /**
     * t2线程是否运行的标志
     * 避免虚假唤醒
     */
    private static boolean flag = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (!flag) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
            log.debug("1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                flag = true;
                log.debug("2");
                lock.notify();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
