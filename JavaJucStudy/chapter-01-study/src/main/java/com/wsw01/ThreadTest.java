package com.wsw01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @version 2025/9/20 20:10
 * @since 1.0
 */
@Slf4j(topic = "c.ThreadTest")
public class ThreadTest {
    public static void main(String[] args) {
        Thread01 thread01 = new Thread01();
        thread01.setName("thread-1");
        thread01.start();

        Thread thread02 = new Thread(new Thread02(),"thread-2");
        thread02.start();
    }
}
