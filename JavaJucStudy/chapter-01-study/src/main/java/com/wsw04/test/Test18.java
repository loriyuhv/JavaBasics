package com.wsw04.test;

/**
 * @author loriyuhv
 * @version 1.0 2025/9/24 17:11
 */
public class Test18 {
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        synchronized (lock) {
            lock.wait();
        }
    }
}
