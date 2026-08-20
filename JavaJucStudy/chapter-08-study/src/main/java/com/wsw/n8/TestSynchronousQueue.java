package com.wsw.n8;

import com.wsw.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/20 08:52
 * @since 1.0
 */
@Slf4j(topic = "c.TestSynchronousQueue")
public class TestSynchronousQueue {
    public static void main(String[] args) {
        SynchronousQueue<Integer> integers = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                log.debug("putting {}", 1);
                integers.put(1);
                log.debug("{} putted ...", 1);

                log.debug("putting {}", 2);
                integers.put(2);
                log.debug("{} putted ...", 2);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "t1").start();

        Sleeper.sleep(1000);

        new Thread(() -> {
            try {
                log.debug("taking {}", 1);
                integers.take();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "t2").start();

        Sleeper.sleep(1000);

        new Thread(() -> {
            try {
                log.debug(" taking {}", 2);
                integers.take();
            } catch (InterruptedException e)  {
                log.error(e.getMessage(), e);
            }
        }, "t3").start();
    }
}
