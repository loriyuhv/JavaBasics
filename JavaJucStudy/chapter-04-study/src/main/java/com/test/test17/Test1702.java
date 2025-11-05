package com.test.test17;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 优化Test1701
 *
 * @author loriyuhv
 * @version 1.0 2025/11/6 3:11
 * @since 1.0
 */
@Slf4j
public class Test1702 {
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

@Getter
class Room {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized void decrement() {
        counter--;
    }

}
