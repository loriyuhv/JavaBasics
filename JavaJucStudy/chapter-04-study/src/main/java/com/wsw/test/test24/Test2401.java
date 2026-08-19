package com.wsw.test.test24;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/19 14:06
 * @since 1.0
 */
@Slf4j(topic = "c.Test2401")
public class Test2401 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 创建一个新的条件变量（休息室）
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        lock.lock();
        // 进入休息室等待
        condition1.await();
        condition2.await();


        condition1.signal();
        condition1.signalAll();

        lock.unlock();


    }
}
