package com.wsw.java;

import org.junit.jupiter.api.Test;

/**
 * String的基本使用：体现String的不可变特性。
 * @author loriyuhv
 * @version 1.0 2025/10/5 13:26
 * @since 1.0
 */
public class TestString1 {
    @Test
    public void test1() {
        String s1 = "abc"; // 字面量定义的方式，"abc"存储在字符串常量池中。
        String s2 = "abc";
        // System.out.println(s1 == s2); // 判断地址：true

        s1 = "hello";
        // System.out.println(s1 == s2); // 判断地址：false

        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "abc";
        // System.out.println(s1 == s2); // 判断地址：true

        s2 += "def";
        // System.out.println(s1 == s2); // 判断地址：false

        System.out.println(s2);
        System.out.println(s1);
    }

    @Test
    public void test3() {
        String s1 = "abc";
        String s2 = s1.replace("a", "m");

        System.out.println(s1);
        System.out.println(s2);
    }
}
