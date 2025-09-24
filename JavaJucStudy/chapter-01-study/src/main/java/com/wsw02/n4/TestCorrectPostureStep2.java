package com.wsw02.n4;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/24 17:47
 * @description
 */
@Slf4j
public class TestCorrectPostureStep2 {
    static final Object room = new Object();
    static boolean isCigarette = false; // 是否有烟
    static boolean isTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                log.debug("小南有烟没？[{}]", isCigarette);
                if (!isCigarette) {
                    log.debug("小南没烟，先歇会！");
                    try {
                        room.wait(2000);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
                log.debug("小南有烟了没？[{}]", isCigarette);
                if (isCigarette) {
                    log.debug("小南可以开始干活了");
                }
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {
                    log.debug("可以开始干活了");
                }
            }, "其他人" + i + 1).start();
        }

        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (room) {
                isCigarette = true;
                log.debug("烟送到了哦！");
                room.notify();
            }
        }, "送烟的").start();
    }
}
