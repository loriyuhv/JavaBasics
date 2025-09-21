package com.wsw02.n3;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/21 13:05
 * @description
 */
@Slf4j
public class EJoin01 {
    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
        test();
        // test99();
    }

    public static void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
            }
            r1 = 10;
        }, "t1");

        long start = System.currentTimeMillis();
        t1.start();
        // 线程执行结束会导致join结束
        log.debug("join begin");
        t1.join(3000);
        log.debug("join end");
        long end = System.currentTimeMillis();
        log.debug("r1: {} r2: {} cost: {}", r1, r2, (end - start));
    }

    /**
     * @description 查看主线程（这里用t2代替）状态
     */
    public static void test99() throws InterruptedException {
        Thread t2 = new Thread(() -> {
            try {
                test();
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
            }
        }, "t2");

        t2.start();
        log.debug("t2 state: {}", t2.getState());
        Thread.sleep(50);
        log.debug("t2 state: {}", t2.getState());
    }

}
