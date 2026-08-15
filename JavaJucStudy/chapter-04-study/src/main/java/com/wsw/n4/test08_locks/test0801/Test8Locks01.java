package com.wsw.n4.test08_locks.test0801;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程八锁
 * 情况1：打印12
 * 情况2：打印21
 *
 * @author loriyuhv
 * @version 1.0 2025/9/22 14:20
 * @since 1.0
 */
@Slf4j(topic = "c.Test8Locks01")
public class Test8Locks01 {
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
    }
}

@Slf4j(topic = "c.Number")
class Number {
    public synchronized void a() {
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }
}
