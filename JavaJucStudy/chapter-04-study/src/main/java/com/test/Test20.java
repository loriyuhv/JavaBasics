package com.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/25 6:47
 * @description 同步模式之保护性暂停
 */
@Slf4j
public class Test20 {
    /**
     * 线程1 等待 线程2 的结果
     * @param args main参数
     */
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        Thread t1 = new Thread(() -> {
            // 等待结果
            Object object = guardedObject.get(2000);
            log.debug("t1等待结果：{}", object);
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            log.debug("t2获取结果");
            guardedObject.complete(new Object());
        }, "t2");
        t2.start();
    }
}

// 增加超时效果
@Slf4j
class GuardedObject {
    // 结果
    private Object response;

    /**
     * 获取结果
     * @param timeout 等待时间
     * @return 结果
     */
    public Object get(long timeout) {
        synchronized (this) {
            // 开始时间
            long start = System.currentTimeMillis();
            // 经历时间
            long passedTime = 0;
            // 没有结果
            while (response == null) {
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
        return response;
    }

    // 产生结果
    public void complete(Object response) {
        synchronized (this) {
            // 给结果成员变量赋值
            this.response = response;
            this.notify();
        }
    }
}
