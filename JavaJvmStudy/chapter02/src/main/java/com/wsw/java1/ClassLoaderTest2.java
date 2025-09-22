package com.wsw.java1;

/**
 * @author loriyuhv
 * @date 2025/9/22 10:54
 * @description
 */
public class ClassLoaderTest2 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.String");
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println(classLoader); // 获取的Bootstrap Class Loader，C实现，所以获取不到

        // 获取系统的ClassLoader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
    }
}
