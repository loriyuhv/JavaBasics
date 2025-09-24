package com.wsw02.n4;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/24 17:47
 * @description 虚假唤醒
 */
@Slf4j
public class TestCorrectPostureStep4 {
    static final Object room = new Object();
    static boolean isCigarette = false; // 是否有烟
    static boolean isTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (room) {
                log.debug("小南有烟没？[{}]", isCigarette);
                // if (!isCigarette) {
                // 利用自旋解决部分唤醒问题
                while (!isCigarette) {
                    log.debug("小南没烟，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
                log.debug("小南有烟了没？[{}]", isCigarette);
                if (isCigarette) {
                    log.debug("小南可以开始干活了");
                } else {
                    log.debug("小南没干成活");
                }
            }
        }, "小南");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (room) {
                log.debug("小女外卖送到了没？[{}]", isTakeout);
                while (!isTakeout) {
                    log.debug("小女没外卖，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
                log.debug("小女外卖送到了吗？[{}]", isTakeout);
                if (isTakeout) {
                    log.debug("小女可以开始干活了");
                } else {
                    log.debug("小女没干成活");
                }
            }
        }, "小女");
        t2.start();

        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (room) {
                isTakeout = true;
                log.debug("外卖送到了哦！");
                // room.notify(); // 虚假唤醒
                room.notifyAll();
            }
        }, "送外卖的").start();

        new Thread(() -> {
            synchronized (room) {
                isCigarette = true;
                log.debug("烟送到了哦！");
                room.notifyAll();
            }
        }, "送烟的").start();
    }
}
