package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/22 14:06
 * @description 优化Test17
 */
@Slf4j
public class Test1702 {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500000000; i++) {
                room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500000000; i++) {
                room.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();

        Thread.sleep(1000);
        log.debug("t1 state: {}", t1.getState());
        log.debug("t2 state: {}", t2.getState());
        t1.join();
        t2.join();
        log.debug("counter: {}", room.getCounter());
    }
}

class Room {
    private int counter;

    public synchronized void increment() {
        // synchronized (this) {
        //     counter++;
        // }
        counter++;
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

