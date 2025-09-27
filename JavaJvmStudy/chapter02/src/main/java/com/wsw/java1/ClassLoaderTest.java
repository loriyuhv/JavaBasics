package com.wsw.java1;

import java.sql.DriverManager;

/**
 * description
 *
 * @author loriyuhv
 * @since 1.0
 * @version 1.0 2025/9/22 10:44
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        // 获取当前类的ClassLoader
        Class<ClassLoaderTest> clazz = ClassLoaderTest.class;
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println(classLoader);

        // 获取当前线程上下文的ClassLoader
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);

        // 获取系统的ClassLoader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取调用者的ClassLoader

    }
}
