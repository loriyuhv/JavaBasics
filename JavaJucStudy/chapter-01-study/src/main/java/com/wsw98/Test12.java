package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/21 13:28
 * @description interrupt打断Runnable状态的线程
 */
@Slf4j
public class Test12 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.debug("被打断了，退出循环");
                    break;
                }
            }
        });
        t1.start();

        Thread.sleep(500);
        log.debug("t1 state: {}", t1.getState());
        log.debug("interrupt");
        t1.interrupt();
        Thread.sleep(500);
        log.debug("t1 state: {}", t1.getState());
        log.debug("打断标记：{}", t1.isInterrupted());
    }
}
