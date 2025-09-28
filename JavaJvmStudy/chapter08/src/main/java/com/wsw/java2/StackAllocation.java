package com.wsw.java2;

/**
 * 栈上分配测试
 * -Xms1G -Xmx1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * -Xms1G -Xmx1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 17:11
 * @since 1.0
 */
public class StackAllocation {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10_000_000; i++) {
            allocate();
        }

        long end = System.currentTimeMillis();
        System.out.println("total time: " + (end - start) + "ms");

        Thread.sleep(1000000);
    }

    private static void allocate() {
        User user = new User(); // 未发生逃逸
    }

    static class User {

    }
}
