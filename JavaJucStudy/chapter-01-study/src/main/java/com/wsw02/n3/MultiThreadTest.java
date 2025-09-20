package com.wsw02.n3;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/20 22:02
 * @description 演示多个线程并发交替执行
 */
@Slf4j
public class MultiThreadTest {
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
