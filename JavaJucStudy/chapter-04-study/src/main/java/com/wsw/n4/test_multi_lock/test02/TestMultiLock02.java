package com.wsw.n4.test_multi_lock.test02;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/18 13:32
 * @since 1.0
 */
public class TestMultiLock02 {
    public static void main(String[] args) {
        BigRoom bigRoom = new BigRoom();
        new Thread(bigRoom::study, "小南").start();
        new Thread(bigRoom::sleep, "小女").start();
    }
}

@Slf4j(topic = "c.BigRoom")
class BigRoom {
    private final Object studyLock = new Object();
    private final Object sleepLock = new Object();

    public void sleep() {
        synchronized (sleepLock) {
            log.debug("sleeping 2 小时");
            Sleeper.sleep(2000);
        }
    }

    public void study() {
        synchronized (studyLock) {
            log.debug("study 1 小时");
            Sleeper.sleep(1000);
        }
    }
}
