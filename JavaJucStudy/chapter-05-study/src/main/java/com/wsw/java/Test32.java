package com.wsw.java;

import lombok.extern.slf4j.Slf4j;

/**
 * 可见性问题：退不出的循环
 *
 * @author loriyuhv
 * @version 1.0 2025/10/6 9:31
 * @since 1.0
 */
@Slf4j
public class Test32 {
    static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (run) {
                log.debug("t is running.");
            }
        }, "t");
        t.start();

        Thread.sleep(1000);
        log.debug("停止t");
        run = false;
    }
}
