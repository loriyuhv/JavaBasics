package com.wsw.test;

import com.wsw.util.Sleeper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/19 15:20
 * @since 1.0
 */
@Slf4j(topic = "c.Test31")
public class Test31 {
    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void main(String[] args) {
        ParkUnpark pu = new ParkUnpark(5);
        t1 = new Thread(() -> pu.print("a", t2), "t1");
        t2 = new Thread(() -> pu.print("b", t3), "t2");
        t3 = new Thread(() -> pu.print("c", t1), "t3");
        t1.start();
        t2.start();
        t3.start();

        Sleeper.sleep(100);
        LockSupport.unpark(t1);
    }
}

@AllArgsConstructor
@Slf4j(topic = "c.ParkUnpark")
class ParkUnpark {
    private int loopNumber;

    public void print(String msg, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            log.debug(msg);
            LockSupport.unpark(next);
        }
    }

}
