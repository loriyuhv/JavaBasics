package com.test.test08.test0804;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程八锁
 * 先打印2，1秒后再打印1
 *
 * @author loriyuhv
 * @version 1.0 2025/11/6 4:18
 * @since 1.0
 */
@Slf4j
public class Test8Locks04 {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();

        new Thread(() -> {
            log.debug("t1 begin");
            n1.a();
        }).start();

        new Thread(() -> {
            log.debug("t2 begin");
            n2.b();
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

