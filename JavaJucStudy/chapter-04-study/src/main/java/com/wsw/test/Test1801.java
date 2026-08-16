package com.wsw.test;

/**
 * wait、notify、notifyAll这些方法，必须获得对象锁，才能调用
 *
 * @author loriyuhv
 * @version 1.0 2025/9/24 17:11
 */
public class Test1801 {
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        synchronized (lock) {
            lock.wait();
        }
    }
}
