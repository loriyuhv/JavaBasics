package com.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程方式3
 *
 * @author loriyuhv
 * @version 1.0 2026/8/14 15:51
 * @since 1.0
 */
@Slf4j(topic = "c.Test02")
public class Test02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            log.debug("Hello Callable!");
            Thread.sleep(1000);
            return 66;
        };

        FutureTask<Integer> task = new FutureTask<>(callable);

        Thread t = new Thread(task, "t");
        t.start();

        Integer result = task.get();
        log.debug("result:{}", result);
    }
}
