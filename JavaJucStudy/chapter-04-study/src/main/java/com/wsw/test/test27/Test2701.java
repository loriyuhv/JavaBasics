package com.wsw.test.test27;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 输出内容 flag 等待标记  下一个标记
 * a        1               2
 * b        2               3
 * c        3               1
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 14:53
 * @since 1.0
 */
@Slf4j(topic = "c.Test2701")
public class Test2701 {
    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify(1, 5);
        new Thread(()-> wn.print("a", 1, 2)).start();
        new Thread(()-> wn.print("b", 2, 3)).start();
        new Thread(()-> wn.print("c", 3, 1)).start();
    }

}

@AllArgsConstructor
@Slf4j(topic = "c.WaitNotify")
class WaitNotify {
    /**
     * 等待标记
     */
    private int flag;

    /**
     * 循环次数
     */
    private int loopNumber;

    /**
     * 打印方法
     */
    public void print(String msg, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (this.flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }

                log.debug(msg);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
