package com.wsw;

/**
 * description 线程调试
 *
 * @author loriyuhv
 * @version 2025/9/26 13:37
 * @since 1.0
 */
public class Debug06 {
    public static void main(String[] args) {
        test("t1");
        test("t2");
    }

    public static void test(String threadName) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }, threadName).start();
    }
}
