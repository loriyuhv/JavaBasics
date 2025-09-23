package com.wsw.java3;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/23 17:40
 * @description 面试题：方法中定义的局部变量是否线程安全？具体情况具体分析
 * 线程安全？
 *      如果只有一个线程才可以操作此数据，则必是线程安全的。
 *      如果有多个线程操作此数据，则此数据是共享数据。如果不考虑同步机制的话，会存在线程安全问题。
 */
@Slf4j
public class StringBuilderTest {
    // s1的声明方式是线程安全的
    public static void method1() {
        // StringBuilder: 线程不安全
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
    }
    // stringBuilder的操作过程，是线程不安全的
    public static void method2(StringBuilder stringBuilder) {
        stringBuilder.append("a");
        stringBuilder.append("b");
    }

    // s1的操作过程，是线程不安全的。
    public static void main(String[] args) throws InterruptedException {
        StringBuilder s = new StringBuilder();
        Thread t1 = new Thread(() -> {
            s.append("a");
            s.append("b");
        });
        t1.start();
        method2(s);
        t1.join();
        System.out.println(s);
    }

}
