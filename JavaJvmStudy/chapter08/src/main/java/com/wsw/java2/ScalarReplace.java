package com.wsw.java2;

/**
 * 标量替换测试
 * -Xms100m -Xmx100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 * -Xms100m -Xmx100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 20:04
 * @since 1.0
 */
public class ScalarReplace {
    public static class User {
        public int id;
        public String name;
    }

    public static void allocate() {
        User user = new User(); // 未发生逃逸
        user.id = 1;
        user.name = "Jack";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++) {
            allocate();
        }
        long end = System.currentTimeMillis();
        System.out.println("total time: " + (end - start) + "ms");
    }
}
