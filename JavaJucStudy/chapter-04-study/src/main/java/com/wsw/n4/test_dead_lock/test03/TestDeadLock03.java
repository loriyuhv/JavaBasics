package com.wsw.n4.test_dead_lock.test03;

import com.wsw.util.Sleeper;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 优化死锁——破坏循环等待条件
 * 线程饥饿
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 12:42
 * @since 1.0
 */
public class TestDeadLock03 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("筷子 1");
        Chopstick c2 = new Chopstick("筷子 2");
        Chopstick c3 = new Chopstick("筷子 3");
        Chopstick c4 = new Chopstick("筷子 4");
        Chopstick c5 = new Chopstick("筷子 5");

        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        // new Philosopher("阿基米德", c5, c1).start();
        new Philosopher("阿基米德", c1, c5).start();
    }
}

@Slf4j(topic = "c.Philosopher")
class Philosopher extends Thread {
    private final Chopstick left;
    private final Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            // 尝试获得左手筷子
            synchronized (left) {
                // 尝试获取右手筷子
                synchronized (right) {
                    eat();
                }
            }
        }
    }

    private void eat() {
        log.debug("eating...");
        Sleeper.sleep(100);
    }

}

@ToString
@AllArgsConstructor
class Chopstick {
    private String name;
}
