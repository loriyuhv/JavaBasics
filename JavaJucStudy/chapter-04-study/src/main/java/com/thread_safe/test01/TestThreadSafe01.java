package com.thread_safe.test01;

import java.util.ArrayList;

/**
 * 参考04.016线程安全分析-局部变量引用
 *
 * @author loriyuhv
 * @version 1.0 2025/11/6 5:16
 * @since 1.0
 */
public class TestThreadSafe01 {
    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {
        ThreadUnsafe test = new ThreadUnsafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
        }
    }
}

class ThreadUnsafe {
    ArrayList<String> list = new ArrayList<>();

    public void method01(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            method2();
            method3();
        }
    }

    private void method2() {
        list.add("1");
    }

    private void method3() {
        list.remove(0);
    }
}
