package com.n3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程状态：New、Runnable、Waiting、TimedWaiting、Blocked、Terminated
 * join: WAITING
 * sleep: TIMED_WAITING
 * @author loriyuhv
 * @version 1.0 2025/9/21 21:57
 */
@Slf4j(topic = "c.TestState")
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                display();
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true) {
                }
            }
        };
        t2.start();

        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                display();
            }
        };
        t3.start();

        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized (TestState.class) {
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        log.debug(e.getMessage(), e);
                    }
                }
            }
        };
        t4.start();

        Thread t5 = new Thread("t5") {
            @SneakyThrows
            @Override
            public void run() {
                t2.join();
            }
        };
        t5.start();

        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                synchronized (TestState.class) {
                    log.debug("t6 running");
                }
            }
        };
        t6.start();

        log.debug("t1 state: {}", t1.getState());
        log.debug("t2 state: {}", t2.getState());
        log.debug("t3 state: {}", t3.getState());
        log.debug("t4 state: {}", t4.getState());
        log.debug("t5 state: {}", t5.getState());
        log.debug("t6 state: {}", t6.getState());
    }

    private static void display() {
        log.debug("running ...");
    }
}
