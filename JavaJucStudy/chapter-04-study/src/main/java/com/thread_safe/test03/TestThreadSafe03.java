package com.thread_safe.test03;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * 参考04.016线程安全分析-局部变量引用，线程安全示例2
 * 子类重写 method3 后另起线程操作 list，导致父子线程并发读写同一 ArrayList，
 * 可能出现：1. ConcurrentModificationException  2. 下标越界  3. 数据丢失
 * 优化方式1：父类方法用private修饰，private修饰的方法不可被子类重写
 * 优化方式2：父类方法用final修饰，也可以实现不能被子类重写
 *
 * @author loriyuhv
 * @version 1.0 2025/11/6 5:38
 * @since 1.0
 */
@Slf4j
public class TestThreadSafe03 {
    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 500;

    public static void main(String[] args) {
        ThreadSafeSubClass test = new ThreadSafeSubClass();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> test.method01(LOOP_NUMBER), "Thread" + (i + 1)).start();
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

    public void method2(ArrayList<String> list) {
        list.add("1");
    }

    public void method3(ArrayList<String> list) {
        list.remove(0);
    }
}

@Slf4j
class ThreadSafeSubClass extends ThreadSafe {
    @Override
    public void method3(ArrayList<String> list) {
        new Thread(() -> list.remove(0)).start();
    }
}
