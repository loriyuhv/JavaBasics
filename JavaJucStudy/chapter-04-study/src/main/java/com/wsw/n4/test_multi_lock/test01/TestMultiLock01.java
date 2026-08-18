package com.wsw.n4.test_multi_lock.test01;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * 多把不想干的锁
 *
 * @author loriyuhv
 * @version 1.0 2026/8/18 12:30
 * @since 1.0
 */
@Slf4j(topic = "c.TestMultiLock")
public class TestMultiLock01 {
    public static void main(String[] args) {
        BigRoom bigRoom = new BigRoom();
        new Thread(bigRoom::study, "小南").start();
        new Thread(bigRoom::sleep, "小女").start();
    }

}

@Slf4j(topic = "c.BigRoom")
class BigRoom {
    public void sleep() {
        synchronized (this) {
            log.debug("sleeping 2 小时");
            Sleeper.sleep(2000);
        }
    }

    public void study() {
        synchronized (this) {
            log.debug("study 1 小时");
            Sleeper.sleep(1000);
        }
    }
}
