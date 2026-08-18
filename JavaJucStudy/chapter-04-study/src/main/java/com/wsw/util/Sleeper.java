package com.wsw.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/18 11:39
 * @since 1.0
 */
@Slf4j(topic = "c.Sleeper")
public class Sleeper {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
