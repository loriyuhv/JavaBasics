package com.wsw04.test;

/**
 * @author loriyuhv
 * @date 2025/9/24 17:11
 * @description
 */
public class Test18 {
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        synchronized (lock) {
            lock.wait();
        }
    }
}
