package com.wsw98;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author loriyuhv
 * @date 2025/9/21 12:04
 * @description TimeUnit类
 */
@Slf4j
public class Test08 {
    public static void main(String[] args) throws InterruptedException {
        log.debug("enter");
        TimeUnit.SECONDS.sleep(1);
        log.debug("end");
    }
}
