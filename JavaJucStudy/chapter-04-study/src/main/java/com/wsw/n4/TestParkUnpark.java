package com.wsw.n4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

import static com.wsw.util.Sleeper.sleep;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/18 11:37
 * @since 1.0
 */
@Slf4j(topic = "c.TestParkUnpark")
public class TestParkUnpark {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("start ...");
            // sleep(1000);
            sleep(2000);
            log.debug("park ...");
            LockSupport.park();
            log.debug("resume ...");
        }, "t1");
        t1.start();

        sleep(1000);
        log.debug("unpark ...");
        LockSupport.unpark(t1);
    }
}
