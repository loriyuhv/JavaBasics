package com.wsw.n8.test_submit;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * submit() 提交任务task，用返回值Future获得任务执行结果
 *
 * @author loriyuhv
 * @version 1.0 2026/8/20 09:11
 * @since 1.0
 */
@Slf4j(topic = "c.TestSubmit01")
public class TestSubmit01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<String> future = pool.submit(() -> {
            log.debug("running...");
            Sleeper.sleep(1000);
            return "ok";
        });

        log.debug("{}", future.get());
    }
}
