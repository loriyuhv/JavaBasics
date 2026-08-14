package com.wsw;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止模式结合犹豫模式
 *
 * @author loriyuhv
 * @version 1.0 2025/10/6 10:32
 * @since 2.0 同步模式之Balking
 */
@Slf4j
public class TestTwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination obj = new TwoPhaseTermination();
        log.debug("开始监控");
        obj.start();
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

    /* 终止标记 */
    volatile private boolean terminated;

    /* 开始标记 */
    private boolean started;

    /* 启动监控线程 */
    public void start() {
        synchronized (this) {
            if (started) {
                return;
            }
            started = true;
        }
        monitorThread = new Thread(() -> {
            while (true) {
                /* 是否终止 */
                if (terminated) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }, "monitor");
        monitorThread.start();
    }

    /* 停止监控线程 */
    public void stop() {
        /* 终止 */
        terminated = true;
        /* 例如线程在执行Thread.sleep(10000);需要打断 */
        monitorThread.interrupt();
    }
}
