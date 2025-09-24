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
            Object object = guardedObject.get();
            log.debug("t1等待结果：{}", object.toString());
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            log.debug("t2获取结果");
            guardedObject.complete(new Object());
        }, "t2");
        t2.start();
    }
}

@Slf4j
class GuardedObject {
    // 结果
    private Object response;

    // 获取结果
    public Object get() {
        synchronized (this) {
            // 没有结果
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
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
