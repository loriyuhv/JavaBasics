package com.wsw01;

/**
 * @author loriyuhv
 * @date 2025/9/20 20:10
 * @description
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread01 thread01 = new Thread01();
        thread01.setName("thread-1");
        thread01.start();

        Thread thread02 = new Thread(new Thread02(),"thread-2");
        thread02.start();
    }
}
