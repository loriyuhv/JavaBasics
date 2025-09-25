package com.wsw.guarded_suspension.example01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/25 15:52
 * @description
 */
@Slf4j
public class GuardedObject {
    private Object response;
    private final Object lock = new Object();

    /**
     * 获取结果
     * @return 结果
     */
    public Object getResponse() {
        synchronized (lock) {
            // 条件不满足则等待
            while (response == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
            }
            return response;
        }
    }

    /**
     * 设置结果
     * @param response 结果
     */
    public void setResponse(Object response) {
        synchronized (lock) {
            this.response = response;
            // 条件满足，通知等待线程
            lock.notify();
        }
    }

    /**
     * 虚假唤醒
     */
    public void wakeup() {
        synchronized (lock) {
            lock.notify();
        }
    }
}
