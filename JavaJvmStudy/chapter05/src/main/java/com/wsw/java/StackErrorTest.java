package com.wsw.java;

/**
 * 演示栈中异常情况
 * 默认：count: 20382
 * 设置栈的大小：-Xss256k count: 3993
 * 设置栈的大小：-Xss512k count: 9345
 *
 * @author loriyuhv
 * @since 1.0
 * @version 1.0 2025/9/23 6:57
 */
public class StackErrorTest {
    private static int count = 1;

    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
