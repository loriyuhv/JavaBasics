package com.wsw.java;

/**
 * description 验证：一个类只会被加载一次
 * 虚拟机必须保证一个类<clinit>()方法在多线程下被同步加锁。
 * @author loriyuhv
 * @since 1.0
 * @version 1.0 2025/9/22 10:19
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
        /* 类加载器加载类.class文件三阶段
        * 1）加载 Loading 通过类加载器加载.class到虚拟机内存 ==>
        * 2）链接 Linking ==>
        *   a）验证Verification 文件标识符cafebabe；
        *   b）准备 preparation 类变量赋值默认值；
        *   c）解析 Resolution 比如System.out.print 需要引入print()方法；
        * 3）初始化 initialization 初始static修饰的变量或代码块。
         */
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
