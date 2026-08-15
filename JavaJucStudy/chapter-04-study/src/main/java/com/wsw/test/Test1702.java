package com.wsw.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 解决临界区代码的原子性问题
 * 思考：synchronized实际使用对象锁保证了临界区内代码的原子性，临界区
 * 内的代码对外是不可分割的，不会被线程切换所打断。
 *
 * @author loriyuhv
 * @version 1.0 2026/8/15 15:57
 * @since 1.0
 */
@Slf4j(topic = "c.Test1702")
public class Test1702 {
    static int count = 0;
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (lock) {
                    count++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (lock) {
                    count--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("count:{}", count);
    }
}
