package com.wsw.guarded_suspension.example03;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 结果类
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 6:17
 * @since 1.0
 */
@Slf4j
public class GuardedObject {
    /* 标识GuardedObject */
    @Getter
    private final int id;
    /* 结果 */
    private String response;

    public GuardedObject(int id) {
        this.id = id;
    }

    /**
     * 获取结果，超时版本
     * @return 结果
     */
    public String getResponse(long timeout) {
        synchronized (this) {
            /* 开始时间 */
            long start = System.currentTimeMillis();
            /* 延迟时间 */
            long delay = 0;

            while (response == null) {
                /* 这一轮循环应该等待的时间 */
                long waitTime = timeout - delay;
                /* 延迟时间超过了最大等待时间，循环终止 */
                if (waitTime <= 0) {
                    break;
                }

                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }

                /* 计算延迟时间 */
                delay = System.currentTimeMillis() - start;
            }
            return response;
        }
    }

    /**
     * 设置结果
     * @param response 结果值
     */
    public void setResponse(String response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
