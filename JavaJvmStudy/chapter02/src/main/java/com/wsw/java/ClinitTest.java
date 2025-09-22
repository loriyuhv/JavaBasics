package com.wsw.java;

/**
 * @author loriyuhv
 * @date 2025/9/22 10:03
 * @description
 */
public class ClinitTest {
    // 任何一个类声明以后，内部至少存在一个类的构造器
    private int a = 1;
    private static int b = 2;

    public static void main(String[] args) {
        int c = 2;
    }

    public ClinitTest() {
        a = 10;
        int d = 20;
    }
}
