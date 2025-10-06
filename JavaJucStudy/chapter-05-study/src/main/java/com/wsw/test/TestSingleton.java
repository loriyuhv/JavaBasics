package com.wsw.test;

/**
 * 单例模式测试
 *
 * @author loriyuhv
 * @version 1.0 2025/10/6 11:09
 * @since 1.0
 */
public class TestSingleton {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance2);

        instance.test();
    }
}

class Singleton {
    private Singleton() {}

    private static Singleton instance;

    public static Singleton getInstance() {
        synchronized (Singleton.class) {
            if (instance != null) {
                return instance;
            }
        }
        instance = new Singleton();
        return instance;
    }

    /* 添加一个实例方法，表明这个类有实例行为 */
    public void test() {
        System.out.println("singleton test");
    }
}
