package com.wsw02.n4.test08.test0807;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/22 15:04
 * @description
 */
@Slf4j
public class Test0807 {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();
        new Thread(()->{
            log.debug("t1 begin");
            try {
                n1.a();
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
            }
        }).start();

        new Thread(()->{
            log.debug("t2 begin");
            n2.b();
        }).start();
    }
}
