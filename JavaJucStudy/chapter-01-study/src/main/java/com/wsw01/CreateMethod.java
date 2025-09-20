package com.wsw01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author loriyuhv
 * @date 2025/9/20 20:02
 * @description
 */
@Slf4j
public class CreateMethod {
    public static void main(String[] args) {
        // 方式一
        // 创建线程对象
        // Thread t = new Thread() {
        //     public void run() {
        //         log.debug("线程创建了。。。");
        //     }
        // };
        // Thread t = new Thread(() -> display());
        // Thread t = new Thread(() -> log.debug("线程创建了。。。"), "Thread-1");

        // 启动线程
        // t.setName("thread-1");
        // t.start();

        // 方式二
        // 创建Runnable对象
        // Runnable runnable = new Runnable() {
        //     @Override
        //     public void run() {
        //         log.debug("{}创建了。", Thread.currentThread().getName());
        //     }
        // };
        // Runnable runnable = () -> display();
        // Thread t2 = new Thread(runnable,"thread-2");
        // t2.start();


        // 创建方式三
        FutureTask<Integer> task3 = new FutureTask<>(() -> {
            display();
            Thread.sleep(2000);
            // int i = 1 / 0;
            return 100;
        });

        Thread t3 = new Thread(task3, "thread-3");
        t3.start();
        try {
            log.debug("task3:{}", task3.get());
        } catch (InterruptedException e) {
            log.error("InterruptedException",e);
        } catch (ExecutionException e) {
            log.error("ExecutionException",e);
        }

        log.debug("主线程。。。");
    }

    public static void display() {
        log.debug("{}线程创建了。", Thread.currentThread().getName());
    }
}
