package com.wsw.java;

/**
 * @author loriyuhv
 * @date 2025/9/22 10:19
 * @description 验证：一个类只会被加载一次
 * 虚拟机必须保证一个类<clinit>()方法在多线程下被同步加锁。
 */
public class DeadThreadTest {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "开始");
            DeadThread dead = new DeadThread();
            System.out.println(Thread.currentThread().getName() + "结束");
        };

        Thread t1 = new Thread(r, "线程1");
        Thread t2 = new Thread(r, "线程2");

        t1.start();
        t2.start();
        // 加载 Loading 通过类加载器加载.class到虚拟机内存 ==> 验证Verification 文件标识符cafebaby, 准备 preparation 类变量赋值默认值 解析 Resolution 比如System.out.print 需要引入print()方法 Link链接 ==> 初始化 initialization 初始static修饰的变量或代码块
    }
}

class DeadThread {
    static {
        if (true) {
            System.out.println(Thread.currentThread().getName() + "初始化当前类");
            while (true) {
                // break;
            }
        }
    }
}
