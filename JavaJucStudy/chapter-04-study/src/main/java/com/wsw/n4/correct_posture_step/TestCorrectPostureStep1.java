package com.wsw.n4.correct_posture_step;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @version 1.0 2025/9/24 17:47
 */
@Slf4j
public class TestCorrectPostureStep1 {
    static final Object room = new Object();
    static boolean isCigarette = false; // 是否有烟
    static boolean isTakeout = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (room) {
                log.debug("小南有烟没？[{}]", isCigarette);
                if (!isCigarette) {
                    log.debug("小南没烟，先歇会！");
                    sleep(2);
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

        sleep(1);
        new Thread(() -> {
            // 这里不能加synchronized
            // synchronized (room) {
                isCigarette = true;
                log.debug("烟送到了哦！");
            // }
        }, "送烟的").start();
    }

    static void sleep(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
