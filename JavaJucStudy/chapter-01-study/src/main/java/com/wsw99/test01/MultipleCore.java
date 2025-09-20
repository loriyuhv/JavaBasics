package com.wsw99.test01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/20 19:35
 * @description
 */
@Slf4j
public class MultipleCore {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // int count1 = sum(100, 2000);
        // int count2 = sum(100, 4000);
        // int count3 = sum(100, 2000);
        int[] counts = new int[5];
        for (int i = 0; i <= 2; i++) {
            int id = i;
            new Thread(() -> {

                counts[id] = sum(100, (id + 1) * 1000);
                log.debug("counts: {}", counts[id]);
            }, "线程" + i).start();
            log.debug("线程{}创建", i);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.debug("总时间{}ms", totalTime);
    }

    public static int sum(int number, int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.debug(e.getMessage());
        }
        int sum = 0;
        for (int i = 0; i < number + 1; i++) {
            sum += i;
        }
        return sum;
    }
}
