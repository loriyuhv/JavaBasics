package com.wsw99.test01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author loriyuhv
 * @date 2025/9/20 19:35
 * @description
 */
@Slf4j
public class MultipleCore {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        log.debug("start");
        int[] counts = new int[3];

        // counts[0] = sum(100, 2000);
        // counts[1] = sum(100, 4000);
        // counts[2] = sum(100, 2000);

        FutureTask<Integer> task1 = new FutureTask<>(() -> sum(100, 2000));
        FutureTask<Integer> task2 = new FutureTask<>(() -> sum(100, 4000));
        FutureTask<Integer> task3 = new FutureTask<>(() -> sum(100, 2000));

        new Thread(task1).start();
        new Thread(task2).start();
        new Thread(task3).start();

        counts[0] = task1.get();
        counts[1] = task2.get();
        counts[2] = task3.get();

        log.debug("总数：{}", counts[0] + counts[1] + counts[2]);
        log.debug("end");
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
