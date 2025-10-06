package com.wsw.java;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止模式 volatile改进
 *
 * @author loriyuhv
 * @version 1.0 2025/10/6 10:32
 * @since 1.0
 */
@Slf4j
public class TestTwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination obj = new TwoPhaseTermination();
        log.debug("开始监控");
        obj.start();
        Thread.sleep(3500);
        log.debug("停止监控");
        obj.stop();
    }
}

@Slf4j
class TwoPhaseTermination {
    /* 监控线程 */
    private Thread monitorThread;

    volatile private boolean terminated;

    /* 启动监控线程 */
    public void start() {
        monitorThread = new Thread(() -> {
            while (true) {
                // Thread currentThread = Thread.currentThread();
                /* 是否被打断 */
                // if (currentThread.isInterrupted()) {
                if (terminated) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    /* 因为sleep出现异常后，会清除打断标记，需要重置打断标记 */
                    // currentThread.interrupt();
                    log.error(e.getMessage());
                }
            }
        }, "monitor");

        monitorThread.start();
    }

    /* 停止监控线程 */
    public void stop() {
        terminated = true;
        monitorThread.interrupt();
    }
}
