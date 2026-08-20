package com.wsw.n8.test_submit;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 提交tasks所有任务
 * 只要有一个任务出现异常，后续所有任务都放弃执行
 *
 * @author loriyuhv
 * @version 1.0 2026/8/20 09:18
 * @since 1.0
 */
@Slf4j(topic = "c.TestSubmit02")
public class TestSubmit02 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin 1");
                    Sleeper.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("begin 2");
                    // int i = 2 / 0;
                    Sleeper.sleep(500);
                    return "2";
                },
                () -> {
                    log.debug("begin 3");
                    Sleeper.sleep(2000);
                    return "3";
                }
        ));

        log.debug("begin");
        for (Future<String> future : futures) {
            log.debug("{}", future.get());
        }
    }
}
