package com.wsw99.test05;

/**
 * @author loriyuhv
 * @version 1.0 2026/4/3 14:08
 * @since 1.0
 */
public class ThreadTest {
    static int i;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Runnable task1 = ThreadTest::test;
        Runnable task2 = ThreadTest::test;
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("i ==> " + i);
        long end = System.currentTimeMillis();
        System.out.println("total time => " + (end - start) + "ms");
    }

    public static void  test() {
        int number = 1000000000;
        int localSum = 0;
        for (int j = 0; j < number; j++) {
            localSum++;
        }
        synchronized (Object.class) {
            i += localSum;
        }
    }
}
