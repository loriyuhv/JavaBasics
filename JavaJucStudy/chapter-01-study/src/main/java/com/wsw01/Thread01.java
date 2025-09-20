package com.wsw01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/20 20:09
 * @description
 */
@Slf4j
public class Thread01 extends Thread {
    @Override
    public void run() {
        log.debug("{}创建了。", this.getName());
    }
}
