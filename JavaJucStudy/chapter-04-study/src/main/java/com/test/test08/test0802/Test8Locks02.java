package com.test.test08.test0802;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程八锁
 * 情况1：线程t1先获取锁，打印1，1秒后打印2
 * 情况2：线程t2先获取锁，打印2，1秒后打印1
 *
 * @author loriyuhv
 * @version 1.0 2025/9/22 14:20
 * @since 1.0
 */
@Slf4j
public class Test8Locks02 {
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

@Slf4j
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
}
