package com.wsw.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 优化Test1702
 *
 * @author loriyuhv
 * @version 1.0 2026/8/15 16:12
 * @since 1.0
 */
@Slf4j(topic = "c.Test1703")
public class Test1703 {
    public static void main(String[] args) throws InterruptedException {
        Room2 room = new Room2();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 6000; i++) {
                room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 6000; i++) {
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

class Room2 {
    private int counter = 0;

    public void increment() {
        synchronized (this) {
            counter++;
        }
    }

    public void decrement() {
        synchronized (this) {
            counter--;
        }
    }

    public int getCounter() {
        synchronized (this) {
            return counter;
        }
    }

}
