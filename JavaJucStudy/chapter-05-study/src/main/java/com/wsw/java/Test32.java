package com.wsw.java;

import lombok.extern.slf4j.Slf4j;

/**
 * 可见性问题：退不出的循环
 * volatile 对比 synchronized
 * synchronized更重量级，耗费资源更多。
 * 可见性推荐volatile
 *
 * @author loriyuhv
 * @version 1.0 2025/10/6 9:31
 * @since 3.0 解决可见性问题 synchronized
 */
@Slf4j
public class Test32 {
    static boolean run = true;

    // 锁对象
    public final static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (!run) {
                        break;
                    }
                }

            }
        }, "t");
        t.start();

        Thread.sleep(1000);
        log.debug("停止t");
        synchronized (lock) {
            run = false;
        }
    }
}
