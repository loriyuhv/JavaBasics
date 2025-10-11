package com.wsw.java2;

/**
 * String intern()方法效率测试，从空间角度
 *
 * @author loriyuhv
 * @version 1.0 2025/10/11 23:11
 * @since 1.0
 */
public class StringIntern2 {
    static final int MAX_COUNT = 1000 * 10000;
    static final String[] arr = new String[MAX_COUNT];

    public static void main(String[] args) {
        Integer[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_COUNT; i++) {
            // 花费的时间：3737 ms String 实例 1000W
            arr[i] = new String(String.valueOf(data[i % data.length])); /* 在堆中创建该字符串对象，并且把引用赋值给arr[i]。如果常量池中存在该字符串对象，不用再创建。 */
            // 花费的时间：1304 ms
            // arr[i] = new String(String.valueOf(data[i % data.length])).intern(); /* 如果常量池存在该字符串，arr[i]存放的是常量池中该字符串的引用 */

            // 46316 ms
            // arr[i] = new String(String.valueOf(i)).intern();
            // 4363 ms
            // arr[i] = new String(String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间：" + (end - start) + " ms");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
