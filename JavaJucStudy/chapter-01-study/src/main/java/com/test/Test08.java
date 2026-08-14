package com.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * TimeUnit类
 * @author loriyuhv
 * @version 1.0 2025/9/21 12:04
 */
@Slf4j(topic = "c.Test08")
public class Test08 {
    public static void main(String[] args) throws InterruptedException {
        log.debug("enter");
        TimeUnit.SECONDS.sleep(1);
        log.debug("end");
    }
}
