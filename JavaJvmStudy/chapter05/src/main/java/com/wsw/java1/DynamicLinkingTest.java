package com.wsw.java1;

/**
 * @author loriyuhv
 * @since 1.0
 * @version 1.0 2025/9/23 13:37
 */
public class DynamicLinkingTest {
    int number = 10;

    public void methodA() {
        System.out.println("methodA ...");
    }

    public void methodB() {
        System.out.println("methodB ...");
        methodA();
        number++;
    }

    public void methodC() {
        methodA();
        number++;
    }
}
