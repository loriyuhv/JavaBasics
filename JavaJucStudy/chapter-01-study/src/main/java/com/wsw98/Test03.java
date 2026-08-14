package com.wsw98;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止案例
 *
 * @author loriyuhv
 * @version 1.0 2026/8/14 11:42
 * @since 1.0
 */
@Slf4j(topic = "c.Test03")
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination two = new TwoPhaseTermination();
        two.start();

        Thread.sleep(3500);
        two.stop();
    }

}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination {
    private Thread monitor;

    /**
     * 启动监控线程
     */
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000); // 情况1
                    log.debug("执行监控记录"); // 情况2
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                    // 重新设计打断标记
                    current.interrupt();
                }
            }
        }, "monitor");
        monitor.start();
    }

    /**
     * 停止监控线程
     */
    public void stop() {
        monitor.interrupt();
    }
}
