package com.wsw02._class;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

/**
 * @author loriyuhv
 * @since 1.0
 * @version 2025/8/18
 */
public class ClassLoaderTest {
    @Test
    public void test1() {
        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1); // null 启动类加载器不是Java写的，所以获取不到
    }

    @Test
    public void test2() throws ClassNotFoundException {
        Class<Order> orderClass = Order.class;
        System.out.println(orderClass.getClassLoader());

        // 对于Java类的核心API，使用引导类加载器加载
        Class<?> stringClass = Class.forName("java.lang.String");
        System.out.println(stringClass.getClassLoader());
    }

    /**
     * 需求：通过ClassLoader加载指定的配置文件
     */
    @Test
    public void test3() throws IOException {
        Properties properties = new Properties();
        // 通过类的加载器读取的文件的默认的路径为：当前的module下的src下
        InputStream fileInputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(fileInputStream);
        Object username = properties.get("jdbc.username");
        System.out.println(username);
    }

    /**
     * 处理属性文件
     */
    @Test
    public void test4() throws IOException {
        Properties properties = new Properties();
        // 读取的文件默认路径为当前的module
        FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/jdbc.properties"));
        properties.load(fileInputStream);
        Object username = properties.get("jdbc.username");
        System.out.println(username);
    }
}
