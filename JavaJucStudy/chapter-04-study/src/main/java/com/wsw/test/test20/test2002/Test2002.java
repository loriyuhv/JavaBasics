package com.wsw.test.test20.test2002;

import lombok.extern.slf4j.Slf4j;

/**
 * Test2001的基础上，增加超时效果
 *
 * @author loriyuhv
 * @version 1.0 2026/8/16 15:47
 * @since 1.0
 */
@Slf4j(topic = "c.Test2002")
public class Test2002 {
    private static final GuardedObject<String> go = new GuardedObject<>();

    public static void main(String[] args) {
        new Thread(() -> {
            log.debug("begin ");
            String result = go.get(1200);
            log.debug("result: {}", result);
        }, "t1").start();
        new Thread(() -> {
            log.debug("begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            // go.complete("Hello");
            go.complete(null); // 虚假唤醒测试
        }, "t2").start();
    }
}

// 增加超时效果
@Slf4j(topic = "c.GuardedObject")
class GuardedObject<T> {
    // 结果
    private T result;

    /**
     * 获取结果
     * @param timeout 等待时间
     * @return 结果
     */
    public T get(long timeout) {
        synchronized (this) {
            // 开始时间
            long start = System.currentTimeMillis();
            // 经历时间
            long passedTime = 0;
            // 没有结果
            while (result == null) {
                // 等待时间
                long waitTime = timeout -passedTime;
                // 经历的时间超过了最大等待时间，退出循环
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                // 求得经历时间
                passedTime = System.currentTimeMillis() - start;
            }
        }
        return result;
    }

    // 产生结果
    public void complete(T result) {
        synchronized (this) {
            // 给结果成员变量赋值
            this.result = result;
            this.notify();
        }
    }
}
