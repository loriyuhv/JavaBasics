package com.wsw02.n4.test08.test0805;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/22 15:04
 * @description
 */
@Slf4j
public class Number {
    public static synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }
}
