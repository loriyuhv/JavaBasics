package com.wsw.test.test26;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 同步模式之顺序控制
 * 固定运行顺序
 * 例子：必须先2后1打印
 * park unpark解法
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 14:32
 * @since 1.0
 */
@Slf4j(topic = "c.Test2501")
public class Test2602 {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.debug("2");
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }
}
