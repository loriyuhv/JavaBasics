package com.wsw02._class;

import org.junit.jupiter.api.Test;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 获取Class实例
 */
public class ClassTest {
    /**
     * 获取Class实例的几种方式（掌握前三种）
     */
    @Test
    public void test1() throws ClassNotFoundException {
        // 1. 调用运行时类的静态属性
        Class<User> userClass1 = User.class;
        System.out.println(userClass1);

        // 2. 调用运行时类的对象的getClass()
        User user = new User();
        Class<? extends User> userClass2 = user.getClass();


        // 3. 调用Class的静态方法forName（String className)
        String className = "com.wsw02._class.User"; // 全类名
        Class<?> userClass3 = Class.forName(className);

        System.out.println(userClass1 == userClass2); // true
        System.out.println(userClass2 == userClass3); // true

        // 4. 使用类的加载器的方式
        Class<?> userClass4 = ClassLoader.getSystemClassLoader().loadClass("com.wsw02._class.User");
        System.out.println(userClass4 == userClass3); // true
    }

    @Test
    public void test2() {
        Class<Object> c1 = Object.class;
        Class<Comparable> c2 = Comparable.class;
        Class<String[]> c3 = String[].class;
        Class<int[][]> c4 = int[][].class;
        // Class c5 = ElementType.class;
        Class<Override> c6 = Override.class;
        Class<Integer> c7 = int.class;
        Class<Void> c8 = void.class;
        Class<Class> c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class<? extends int[]> c10 = a.getClass();
        Class<? extends int[]> c11 = b.getClass();
        // 只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }
}
