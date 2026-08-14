package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * start vs run
 * @author loriyuhv
 * @version 1.0 2025/9/21 11:41
 */
@Slf4j(topic = "c.Test04")
public class Test04 {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running ...");
            }
        };

        t1.run(); // 主线程执行run方法
        t1.start(); // t1线程执行run方法
    }
}
