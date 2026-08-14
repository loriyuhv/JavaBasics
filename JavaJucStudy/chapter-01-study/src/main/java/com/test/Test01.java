package com.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程
 *
 * @author loriyuhv
 * @version 1.0 2026/8/14 15:49
 * @since 1.0
 */
@Slf4j(topic = "c.Test01")
public class Test01 {
    public static void main(String[] args) {
        test01();
        test02();
    }

    /**
     * 创建线程方式一: 创建线程对象
     */
    public static void test01() {
        Thread t1 = new Thread(() -> log.debug("Hello thread!"), "t1");
        t1.start();
    }

    /**
     * 创建线程方式2: Runnable接口方式
     */
    public static void test02() {
        Runnable task = () -> log.debug("Hello runnable!");
        Thread t = new Thread(task, "t");
        t.start();
    }
}
