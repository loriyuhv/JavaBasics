package com.wsw04.other.exercise01;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * @author loriyuhv
 * @date 2025/8/20
 * @description
 */
public class FruitTest {
    @Test
    public void test1() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 1. 读取配置文件信息
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
        properties.load(inputStream);
        String fruitName = (String) properties.get("fruitName");
        System.out.println(fruitName);

        // 2. 通过反射，创建指定全类名对应的类实例
        Class<?> clazz = Class.forName(fruitName);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Fruit o = (Fruit) constructor.newInstance();
        // 3. 通过榨汁机调用run()
        Juicer juicer = new Juicer();
        juicer.run(o);
    }
}
