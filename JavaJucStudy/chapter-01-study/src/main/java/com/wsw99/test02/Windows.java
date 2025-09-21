package com.wsw99.test02;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/21 11:28
 * @description
 */
@Slf4j
public class Windows {
    private int tickets = 100;

    public boolean sell(int num) throws InterruptedException {
        Thread.sleep(5);

        synchronized (this) {
            if (tickets != 0 && num > 0 && num <= tickets) {
                tickets -= num;
                log.debug("{}窗口票数：{}", Thread.currentThread().getName(), tickets);

                return true;
            }
            return false;
        }
    }
}
