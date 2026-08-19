package com.wsw.test.test17;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程安全问题
 * <p>临界区 Critical Section</p>
 * 一个程序运行多个线程本身是没有问题的
 * 问题出现在多个线程访问共享资源：
 *  - 多个线程读共享资源其实也没有问题
 *  - 在多个线程对共享资源读写操作时发生指令交错，就会出现问题
 * 一段代码块内如果存在对共享资源的多线程读写操作，称这段代码块为临界区
 * 下面临界区是
 * {count++} 或 {count--}
 *
 * <p>竞态条件</p>
 * 多个线程在临界区内执行，由于代码的<b>执行序列不同</b>而导致结果无法预见，
 * 称之为发生了竞态条件。
 *
 * <p>临界区安全问题解决方案</p>
 * 应用之互斥
 * - 阻塞式的解决方案：synchronized、Lock
 * - 非阻塞式的解决方案：原子变量
 *
 * @author loriyuhv
 * @version 1.0 2026/8/14 16:16
 * @since 1.0
 */
@Slf4j(topic = "c.Test1701")
public class Test1701 {
    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count--;
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("count:{}", count);
    }
}
