package com.wsw.java1;

/**
 * @author loriyuhv
 * @version 1.0 2025/10/5 17:18
 * @since 1.0
 */
public class TestString4 {
    public static void main(String[] args) {
        System.out.println();       // 2193
        System.out.println("1");    // 2194
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10");   // 2203
        /* 如下的字符串"1"到"10"不会再次加载。 */
        System.out.println("1");    // 2204
        System.out.println("2");    // 2204
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10");   // 2204
    }
}
