package com.wsw.guarded_suspension.example02;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/25 16:08
 * @description 超时版
 */
@Slf4j
public class TimeoutGuardedObject {
    private Object response;
    private final Object lock = new Object();

    /**
     * 获取结果
     * @return 结果
     */
    public Object getResponse(long timeout) {
        synchronized (lock) {
            // 1）记录最初的时间
            long start = System.currentTimeMillis();
            // 2）记录延迟的时间
            long delay = 0;

            // 条件不满足则等待
            while (response == null) {
                // 4）假设timeout 是2000，结果在1000时唤醒了，那么还有1000要等
                long waitTime = timeout - delay;
                if (waitTime <= 0) {
                    log.debug("break!");
                    break;
                }
                try {
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }

                // 3）如果提前被唤醒，这时已经经历的时间设置为
                delay = System.currentTimeMillis() - start;
                log.debug("delay: {}", delay);
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

}
