package com.wsw.test.test20.test2005;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/17 11:34
 * @since 1.0
 */
@Slf4j(topic = "c.Test2005")
public class Test2005 {
    public static void main(String[] args) throws InterruptedException {
        final GuardedObject<String> go = new GuardedObject<>();

        new Thread(() -> {
            log.debug("等待结果");
            String result = go.get(2000);
            log.debug("结果：{}", result);
        }, "t1").start();

        Thread.sleep(1000);

        new Thread(() -> {
            log.debug("生产结果");
            // go.complete("Hello world!!!");
            go.complete(null);
        }, "t2").start();
    }
}

@Slf4j(topic = "c.GuardedObject")
class GuardedObject<T> {
    private T result;

    public synchronized T get(long timeout) {
        long start = System.currentTimeMillis();
        long passedTime = 0;
        while (result == null) {
            long waitTime = timeout - passedTime;
            try {
                if (waitTime <= 0) {
                    break;
                }
                this.wait(waitTime);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            passedTime = System.currentTimeMillis() - start;
        }
        return result;
    }

    public synchronized void complete(T result) {
        this.result = result;
        this.notify();
    }
}
