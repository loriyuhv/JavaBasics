package com.wsw.n4.test08_locks.test0805;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程八锁
 * 情况：先打印2，1秒后再打印1。
 *
 * @author loriyuhv
 * @version 1.0 2025/9/22 15:04
 * @since 1.0
 */
@Slf4j(topic = "c.Test8Locks05")
public class Test8Locks05 {
    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(()->{
            log.debug("t1 begin");
            // n1.a();
            Number.a();
        }).start();

        new Thread(()->{
            log.debug("t2 begin");
            n1.b();
        }).start();
    }
}

@Slf4j(topic = "c.Number")
class Number {
    /* 由于a方法是静态方法，所以锁对象是Number */
    public static synchronized void a() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.debug("1");
    }

    /* 由于b方法是普通方法，所以锁对象是this */
    public synchronized void b() {
        log.debug("2");
    }
}
