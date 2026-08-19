package com.wsw.n4.test_dead_lock.test01;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程活跃性——死锁
 * @author loriyuhv
 * @version 1.0 2026/8/18 13:40
 * @since 1.0
 */
@Slf4j(topic = "c.TestDeadLock")
public class TestDeadLock {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 线程A：先获取resourceB，然后获取resourceA
        Thread threadA = new Thread(() -> {
            synchronized (resourceB) {
                log.debug("线程A持有了资源2");
                try {
                    // 休眠一段时间，确保线程B能获取到resourceA
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }

                log.debug("线程A等待获取资源1");
                synchronized (resourceA) {
                    log.debug("线程A获取了资源1");
                }
            }
        }, "线程A");

        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                log.debug("线程B持有了资源1");
                try {
                    // 休眠一段时间，确保线程A能获取到resourceB
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }

                log.debug("线程A等待获取资源2");
                synchronized (resourceB) {
                    log.debug("线程A获取了资源2");
                }

            }
        }, "线程B");

        threadA.start();
        threadB.start();

        Thread.sleep(2000);

        log.debug("threadA: {}", threadA.isAlive());
        log.debug("threadA state: {}", threadA.getState());
        log.debug("threadB: {}", threadB.isAlive());
        log.debug("threadB state: {}", threadB.getState());
    }
}
