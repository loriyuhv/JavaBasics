package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/22 11:53
 * @description 华罗庚 统筹方法：烧水泡茶
 * 线程1：洗水壶1分钟 ==> 烧开水 8分钟
 * 线程2：洗茶壶1分钟 ==> 洗茶杯1分钟 ==> 拿茶叶1分钟 ==> 泡茶2分钟
 */
@Slf4j
public class Test16 {
    public static void main(String[] args) throws InterruptedException {
        Thread jerry = new Thread(() -> {
            log.debug("洗水壶");
            sleep(1000);
            log.debug("烧开水");
            sleep(8000);
        }, "Jerry");

        Thread tom = new Thread(() -> {
            log.debug("洗茶壶");
            sleep(1000);
            log.debug("洗茶杯");
            sleep(1000);
            log.debug("拿茶叶");
            sleep(1000);
            try {
                jerry.join();
                log.debug("泡茶");
                sleep(2000);
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
            }
        }, "Tom");

        long start = System.currentTimeMillis();
        jerry.start();
        tom.start();
        tom.join();
        long end = System.currentTimeMillis();
        log.debug("total: {}", end - start);
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.debug(e.getMessage());
        }
    }
}
