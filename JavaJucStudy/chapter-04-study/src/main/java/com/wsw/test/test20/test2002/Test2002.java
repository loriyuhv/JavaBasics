package com.wsw.test.test20.test2002;

import com.wsw.pattern.Downloader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * Test2001的基础上，增加超时效果
 *
 * @author loriyuhv
 * @version 1.0 2026/8/16 15:47
 * @since 1.0
 */
@Slf4j(topic = "c.Test2002")
public class Test2002 {
    private static GuardedObject guardedObject = new GuardedObject();

    public static void main(String[] args) {
        new Thread(() -> {
            log.debug("begin ");
            Object o = guardedObject.get(1200);
            // Object o = guardedObject.get(1200);
            log.debug("result: {}", o);
        }, "t1").start();
        new Thread(() -> {
            log.debug("begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            // guardedObject.complete(new Object());
            guardedObject.complete(null); // 虚假唤醒测试
        }, "t2").start();
    }

}

// 增加超时效果
@Slf4j(topic = "c.GuardedObject")
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
