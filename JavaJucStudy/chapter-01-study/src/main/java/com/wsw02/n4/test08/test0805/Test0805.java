package com.wsw02.n4.test08.test0805;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/22 15:04
 * @description
 */
@Slf4j
public class Test0805 {
    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(()->{
            log.debug("t1 begin");
            try {
                // n1.a();
                Number.a();
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
            }
        }).start();

        new Thread(()->{
            log.debug("t2 begin");
            n1.b();
        }).start();
    }
}
