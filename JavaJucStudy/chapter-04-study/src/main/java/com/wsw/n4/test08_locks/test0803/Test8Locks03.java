package com.wsw.n4.test08_locks.test0803;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程八锁
 * 由于c()没有加synchronized关键字，也就是没有加锁。
 * 情况1：先打印3，如果t1线程先获取锁，1秒后并行打印1和2；
 * 情况2：先打印3，如果t2线程先获取锁，打印2，1秒后再打印1；
 * 情况3：如果t2线程优先调度执行，先打印2并行打印3,1秒后再打印1。
 *
 * @author loriyuhv
 * @version 1.0 2025/11/6 4:18
 * @since 1.0
 */
@Slf4j(topic = "Test8Locks03")
public class Test8Locks03 {
    public static void main(String[] args) {
        Number n1 = new Number();

        new Thread(() -> {
            log.debug("t1 begin");
            n1.a();
        }).start();
        new Thread(() -> {
            log.debug("t2 begin");
            n1.b();
        }).start();
        new Thread(() -> {
            log.debug("t3 begin");
            n1.c();
        }).start();
    }
}


@Slf4j(topic = "Number")
class Number {
    public synchronized void a() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }

    public void c() {
        log.debug("3");
    }
}

