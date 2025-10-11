package com.wsw.java2;

/**
 * @author loriyuhv
 * @version 1.0 2025/10/11 23:00
 * @since 1.0
 */
public class StringExer1 {
    public static void main(String[] args) {
        String s = new String("a") + new String("b");
        String s2 = s.intern();
        System.out.println(s2 == "ab"); /* jdk6/7/8: true */
        System.out.println(s == "ab"); /* jdk6: false jdk7/8: true */
    }
}
