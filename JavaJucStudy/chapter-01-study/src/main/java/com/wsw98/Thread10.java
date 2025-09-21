package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/21 12:38
 * @description 案例：防止CPU占用100%
 */
@Slf4j
public class Thread10 {
    public static void main(String[] args) {
        for(int i=0;i<16;i++){
            new Thread(()->{
                log.debug("{}创建了。", Thread.currentThread().getName());
                while(true){
                    // try {
                    //     Thread.sleep(20);
                    // } catch (InterruptedException e) {
                    //     throw new RuntimeException(e);
                    // }
                }
            }).start();
        }
    }
}
