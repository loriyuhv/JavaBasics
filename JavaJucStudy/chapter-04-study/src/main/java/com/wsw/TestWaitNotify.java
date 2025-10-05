package com.wsw;

import lombok.extern.slf4j.Slf4j;

/**
 * RUNNABLE <--> WAITING
 *
 * @author loriyuhv
 * @version 1.0 2025/10/6 7:20
 * @since 1.0
 */
@Slf4j
public class TestWaitNotify {
    final static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                log.debug("t1执行...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
                log.debug("t1执行其他代码...");
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                log.debug("t2执行...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
                log.debug("t2执行其他代码...");
            }
        }, "t2");
        t2.start();

        /* 主线程2秒后执行 */
        Thread.sleep(2000);
        log.debug("t1 state: {}", t1.getState()); // WAITING对应IDEA里的WAIT
        log.debug("t2 state: {}", t2.getState()); // WAITING对应IDEA里的WAIT
        log.debug("唤醒lock上的其他线程");
        synchronized (lock) {
            // lock.notify();
            lock.notifyAll(); // DEBUG到此处线程1和2的状态都是WAITING
        } // DEBUG到此处线程1和2的状态都是BLOCKED，换言之，这个时候主线程还没有释放锁，所以线程进入WaitSet队列等待
    } // DEBUG到此处主线程释放lock，线程1和线程2竞争锁，竞争成功者，状态是RUNNING，否则状态是BLOCKED。
}
