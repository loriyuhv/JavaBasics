package com.wsw.test.test23;

import com.wsw.util.Sleeper;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 优化哲学家就餐问题
 * tryLock
 * 由于ReentrantLock是公平锁
 *
 * @author loriyuhv
 * @version 1.0 2026/8/19 13:20
 * @since 1.0
 */
@Slf4j(topic = "c.Test2301")
public class Test2301 {

    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("筷子01");
        Chopstick c2 = new Chopstick("筷子02");
        Chopstick c3 = new Chopstick("筷子03");
        Chopstick c4 = new Chopstick("筷子04");
        Chopstick c5 = new Chopstick("筷子05");
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();
    }
}

@Slf4j(topic = "c.Philosopher")
class Philosopher extends Thread{
    private final Chopstick left;
    private final Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while(true){
            if (left.tryLock()) {
                // 尝试获取右手筷子
                try {
                    if (right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock(); // 释放自己左手的筷子
                }
            }

        }
    }

    private void eat() {
        log.debug("eating ...");
        Sleeper.sleep(500);
    }

}

@ToString
@AllArgsConstructor
class Chopstick extends ReentrantLock {
    private String name;
}
