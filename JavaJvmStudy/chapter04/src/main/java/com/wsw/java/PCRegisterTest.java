package com.wsw.java;

/**
 * 反编译指令：javap -verbose .\PCRegisterTest.class
 *
 * @author loriyuhv
 * @since 1.0
 * @version 1.0 2025/9/23 5:55
 */
public class PCRegisterTest {
    public static void main(String[] args) {
        int i = 10;
        int j = 20;
        int k = i + j;

        String s = "abc";

        System.out.println(s);
        System.out.println(i);
        System.out.println(k);
    }
}
