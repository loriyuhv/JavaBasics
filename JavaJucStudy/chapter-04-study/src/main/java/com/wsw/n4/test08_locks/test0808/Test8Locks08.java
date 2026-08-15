package com.wsw.n4.test08_locks.test0808;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程八锁
 * 情况1：先打印2，再打印1
 * 情况2：先打印1，再打印2
 * @author loriyuhv
 * @version 1.0 2025/9/22 15:04
 * @since 1.0
 */
@Slf4j(topic = "c.Test8Locks08")
public class Test8Locks08 {
    public static void main(String[] args) {
        /* Number n1 = new Number(); Number n2 = new Number(); */
        new Thread(()->{
            log.debug("t1 begin");
            // n1.a();
            Number.a();
        }).start();

        new Thread(()->{
            log.debug("t2 begin");
            // n2.b();
            Number.b();
        }).start();
    }
}

@Slf4j(topic = "c.Number")
class Number {
    public static synchronized void a() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.debug(e.getMessage());
        }
        log.debug("1");
    }

    public static synchronized void b() {
        log.debug("2");
    }
}
