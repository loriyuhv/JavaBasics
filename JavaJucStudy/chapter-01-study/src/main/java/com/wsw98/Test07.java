package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * 其他线程可以使用interrupt方法打断正在睡眠的线程，
 * 这时sleep会抛出InterruptException异常。
 * @author loriyuhv
 * @version 1.0 2025/9/21 11:58
 */
@Slf4j(topic = "c.Test07")
public class Test07 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("enter sleep ...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("wake up ...");
                    log.debug(e.getMessage());
                }
            }
        };

        t1.start();
        Thread.sleep(1000);
        log.debug("t1 state:{}", t1.getState());
        log.debug("interrupt ...");
        t1.interrupt();
        Thread.sleep(200);
        log.debug("t1 state:{}", t1.getState());
    }
}
