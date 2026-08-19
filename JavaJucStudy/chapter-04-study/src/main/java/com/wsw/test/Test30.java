package com.wsw.test;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 设计模式——交替输出
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 15:06
 * @since 1.0
 */
@Slf4j(topic = "c.Test30")
public class Test30 {

    public static void main(String[] args) {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(()->awaitSignal.print("a", a, b)).start();
        new Thread(()->awaitSignal.print("b", b, c)).start();
        new Thread(()->awaitSignal.print("c", c, a)).start();

        awaitSignal.lock();
        try {
            log.debug("begin ...");
            a.signal();
        } finally {
            awaitSignal.unlock();
        }
    }
}

@AllArgsConstructor
@Slf4j(topic = "c.AwaitSignal")
class AwaitSignal extends ReentrantLock {
    private int loopNumber;

    /**
     * 参数1 打印内容 参数2 进入哪一间休息室等待
     */
    public void print(String msg, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                log.debug(msg);
                next.signal();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            } finally {
                unlock();
            }
        }
    }
}
