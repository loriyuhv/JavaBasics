package com.wsw.n8.test_submit;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 提交tasks中所有任务，哪个任务先成功执行完毕，返回此任务执行结果，其他任务取消
 *
 * @author loriyuhv
 * @version 1.0 2026/8/20 09:28
 * @since 1.0
 */
@Slf4j(topic = "c.TestSubmit03")
public class TestSubmit03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);


        String result = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin 1");
                    Sleeper.sleep(1000);
                    log.debug("end 1");
                    return "1";
                },
                () -> {
                    log.debug("begin 2");
                    Sleeper.sleep(500);
                    log.debug("end 2");
                    return "2";
                },
                () -> {
                    log.debug("begin 3");
                    Sleeper.sleep(2000);
                    log.debug("end 3");
                    return "3";
                }
        ));

        log.debug("result:{}", result);
    }
}
