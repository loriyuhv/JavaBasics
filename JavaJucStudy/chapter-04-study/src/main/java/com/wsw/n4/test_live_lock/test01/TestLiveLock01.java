package com.wsw.n4.test_live_lock.test01;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程活锁
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 12:31
 * @since 1.0
 */
@Slf4j(topic = "c.TestLiveLock01")
public class TestLiveLock01 {
    static volatile int count = 10;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            // 期望减到 0 退出循环
            while (count > 0) {
                Sleeper.sleep(200);
                count--;
                log.debug("count:{}", count);
            }
        }, "t1").start();

        new Thread(() -> {
            // 期望超过 20 退出循环
            while (count < 20) {
                Sleeper.sleep(200);
                count++;
                log.debug(" count:{}", count);
            }
        }, "t2").start();
    }
}
