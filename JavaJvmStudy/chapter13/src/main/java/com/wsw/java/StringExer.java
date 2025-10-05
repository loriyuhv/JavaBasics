package com.wsw.java;

/**
 * @author loriyuhv
 * @version 1.0 2025/10/5 13:04
 * @since 1.0
 */
public class StringExer {
    String str = "good";
    char[] ch = {'t', 'e', 's', 't'};

    public void change(String str, char[] ch) {
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringExer exer = new StringExer();
        exer.change(exer.str, exer.ch);
        System.out.println(exer.str);
        System.out.println(exer.ch);
    }
}