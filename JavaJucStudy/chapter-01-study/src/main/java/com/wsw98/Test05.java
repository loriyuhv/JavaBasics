package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * 获取线程状态信息
 * @author loriyuhv
 * @version 1.0 2025/9/21 11:46
 */
@Slf4j(topic = "c.Test05")
public class Test05 {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running...");
            }
        };

        log.debug("t1线程start前状态：{}", t1.getState());
        t1.start();
        log.debug("t1线程start后状态：{}", t1.getState());
    }
}
