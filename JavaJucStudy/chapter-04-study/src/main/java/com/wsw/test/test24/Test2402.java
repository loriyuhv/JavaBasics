package com.wsw.test.test24;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 条件变量使用
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 14:12
 * @since 1.0
 */
@Slf4j(topic = "c.Test2402")
public class Test2402 {
    static boolean isCigarette = false; // 是否有烟
    static boolean isTakeout = false;
    static final ReentrantLock lock = new ReentrantLock();
    /**
     * 等待烟的休息室
     */
    static final Condition waitCigaretteSet = lock.newCondition();
    /**
     * 等外卖的休息室
     */
    static final Condition waitTakeoutSet = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                log.debug("小南有烟没？[{}]", isCigarette);
                // 利用自旋解决部分唤醒问题
                while (!isCigarette) {
                    log.debug("小南没烟，先歇会！");
                    waitCigaretteSet.await();
                }
                log.debug("小南有烟了，可以开始干活了");
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        }, "小南");
        t1.start();

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                log.debug("小女外卖送到了没？[{}]", isTakeout);
                while (!isTakeout) {
                    log.debug("小女没外卖，先歇会！");
                    waitTakeoutSet.await();
                }
                log.debug("小女有外卖了，可以开始干活了");
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        }, "小女");
        t2.start();

        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            try {
                isTakeout = true;
                log.debug("外卖送到了哦！");
                waitTakeoutSet.signal();
            } finally {
                lock.unlock();
            }
        }, "送外卖的").start();

        new Thread(() -> {
            lock.lock();
            try {
                isCigarette = true;
                log.debug("烟送到了哦！");
                waitCigaretteSet.signal();
            } finally {
                lock.unlock();
            }
        }, "送烟的").start();
    }

}
