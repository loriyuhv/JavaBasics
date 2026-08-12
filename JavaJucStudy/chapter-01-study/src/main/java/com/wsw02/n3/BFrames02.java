package com.wsw02.n3;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @version 1.0 2025/9/21 10:12
 */
@Slf4j
public class BFrames02 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            method1(20);
        }, "t1");
        t1.start();
        method1(10);
    }

    private static void method1(int x){
        int y = x + 1;
        Object o = method2();
        log.debug("{}:{}", o, y);
    }

    private static Object method2(){
        return new Object();
    }
}
