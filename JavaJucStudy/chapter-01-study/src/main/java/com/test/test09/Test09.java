package com.test.test09;

import lombok.extern.slf4j.Slf4j;

/**
 * 一、yield
 * 1）调用yield会让当前线程从Running进入Runnable就绪状态，然后调度其他线程
 * 2）具体实现依赖于操作系统的任务调度器
 * 分析：sleep和yield的最大区别：由于调用sleep()会进入TIMED WAITING状态，这时
 * CPU就不会分配时间片给该线程。但是，调用yield()会进入Runnable状态，还是有概率
 * 发生CPU分配时间片给该线程。
 * 二、优先级 默认：5 最小：1 最大：10
 * @author loriyuhv
 * @version 1.0 2025/9/21 12:07
 */
@Slf4j(topic = "c.Test09")
public class Test09 {
    public static void main(String[] args) throws InterruptedException {
        Runnable task1 = () -> {
            int count = 0;
            for(int i = 0; i < 10000; i++) {
                log.debug("---->1 {}", count++);
            }
        };

        Runnable task2 = () -> {
            int count = 0;
            for(int i = 0; i < 10000; i++) {
                // Thread.yield();
                log.debug("        ---->2 {}", count++);
            }
        };

        Thread t1 = new Thread(task1, "t1");
        Thread t2 = new Thread(task2, "t2");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}
