package com.n3;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示多个线程并发交替执行
 *
 * @author loriyuhv
 * @version 1.0 2025/9/20 22:02
 */
@Slf4j(topic = "c.TestMultiThread")
public class TestMultiThread {
    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                display();
            }
        }, "t1").start();

        new Thread(()->{
            while(true){
                display();
            }
        }, "t2").start();
    }

    public static void display() {
        log.debug("{} running.", Thread.currentThread().getName());
    }
}
