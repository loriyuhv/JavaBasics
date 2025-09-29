package com.wsw.java;

/**
 * 测试设置方法区大小参数的默认值
 * jdk7
 * -XX:PermSize=100m -XX:MaxPermSize=100m
 * jdk8及以后
 * -XX:MetaspaceSize=100m -XX:MaxMetaspaceSize=100m
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 12:29
 * @since 1.0
 */
public class MethodAreaDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start...");
        Thread.sleep(10_000_000);
        System.out.println("end...");
    }
}
