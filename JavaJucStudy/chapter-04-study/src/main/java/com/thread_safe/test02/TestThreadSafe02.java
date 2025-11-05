package com.thread_safe.test02;

import java.util.ArrayList;

/**
 * 参考04.016线程安全分析-局部变量引用
 * 优化TestThreadSafe01
 *
 * @author loriyuhv
 * @version 1.0 2025/11/6 5:26
 * @since 1.0
 */
public class TestThreadSafe02 {
    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {
        ThreadSafe test = new ThreadSafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
        }
    }
}

class ThreadSafe {
    public void method01(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }

    private void method2(ArrayList<String> list) {
        list.add("1");
    }

    private void method3(ArrayList<String> list) {
        list.remove(0);
    }
}

