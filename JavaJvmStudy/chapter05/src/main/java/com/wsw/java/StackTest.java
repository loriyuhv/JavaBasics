package com.wsw.java;

/**
 * @author loriyuhv
 * @since 1.0
 * @version 1.0 2025/9/23 6:46
 */
public class StackTest {
    public static void main(String[] args) {
        StackTest test = new StackTest();
        test.methodA();
    }

    public void methodA() {
        int i = 10;
        int j = 20;

        methodB();
    }

    public void methodB() {
        int k = 30;
        int m = 40;
    }
}
