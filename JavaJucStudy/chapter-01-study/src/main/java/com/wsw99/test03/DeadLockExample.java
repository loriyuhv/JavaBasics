package com.wsw99.test03;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/21 15:43
 * @description
 */
@Slf4j
public class DeadLockExample {
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
        }, "ThreadB");

        threadA.start();
        threadB.start();

        Thread.sleep(2000);

        log.debug("threadA: {}", threadA.isAlive());
        log.debug("threadA state: {}", threadA.getState());
        log.debug("threadB: {}", threadB.isAlive());
        log.debug("threadB state: {}", threadB.getState());
    }
}
