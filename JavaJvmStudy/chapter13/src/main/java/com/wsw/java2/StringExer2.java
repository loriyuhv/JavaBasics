package com.wsw.java2;

/**
 * @author loriyuhv
 * @version 1.0 2025/10/11 23:06
 * @since 1.0
 */
public class StringExer2 {
    public static void main(String[] args) {
        String s1 = new String("ab"); /* 字符串常量池"ab" s1：堆空间"ab" */
        // String s1 = new String("a") + new String("b");
        s1.intern();
        String s2 = "ab"; /* s2: 字符串常量池中"ab" */
        System.out.println(s1 == s2); /* true */
    }
}
