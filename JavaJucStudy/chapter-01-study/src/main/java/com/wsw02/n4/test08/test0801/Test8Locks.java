package com.wsw02.n4.test08.test0801;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/22 14:20
 * @description
 */
@Slf4j
public class Test8Locks {
    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(() -> {
            log.debug("t1 begin");
            try {
                n1.a();
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
            }
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

@Slf4j
class Number {
    public synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }

    public void c() {
        log.debug("3");
    }
}
