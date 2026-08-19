package com.wsw.test.test17;

import lombok.extern.slf4j.Slf4j;

/**
 * 优化Test1703
 * <p>synchronized加载成员方法上，锁住的是this对象</p>
 * class Test {
 *     public synchronized void test() {}
 * }
 * 等价
 * class Test {
 *     public void test() {
 *         synchronized(this) {}
 *     }
 * }
 * <p>synchronized加在静态方法上，锁住的是类对象</p>
 * class Test {
 *     public synchronized static void test() {}
 * }
 * 等价
 * class Test {
 *     public static void test() {
 *         synchronized(Test.class) {}
 *     }
 * }
 *
 * @author loriyuhv
 * @version 1.0 2025/11/6 3:11
 * @since 1.0
 */
@Slf4j(topic = "c.Test1704")
public class Test1704 {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.debug("counter: {}", room.getCounter());
    }
}

class Room {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized void decrement() {
        counter--;
    }

    public synchronized int getCounter() {
        return counter;
    }
}
