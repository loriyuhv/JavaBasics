package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * 案例：防止CPU占用100%
 * @author loriyuhv
 * @version 1.0 2025/9/21 12:38
 */
@Slf4j(topic = "c.Test0901")
public class Test0901 {
    public static void main(String[] args) {
        for(int i=0; i<16; i++){
            new Thread(() -> {
                log.debug("{}创建了。", Thread.currentThread().getName());
                while(true){
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "t" + i).start();
        }
    }
}
