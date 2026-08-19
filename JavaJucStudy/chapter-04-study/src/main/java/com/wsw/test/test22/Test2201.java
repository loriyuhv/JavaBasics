package com.wsw.test.test22;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * 1）支持可重入
 * 2）支持公平锁 synchronized是非公平
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 12:55
 * @since 1.0
 */
@Slf4j(topic = "c.Test2201")
public class Test2201 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            log.debug("enter main");
            method1();
        } finally {
            lock.unlock();
        }
    }

    public static void method1() {
        lock.lock();
        try {
            log.debug("enter method1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    public static void method2() {
        lock.lock();
        try {
            log.debug("enter method2");
        } finally {
            lock.unlock();
        }
    }
}
