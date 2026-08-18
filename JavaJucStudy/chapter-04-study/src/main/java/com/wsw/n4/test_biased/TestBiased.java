package com.wsw.n4.test_biased;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * 注意：JDK15、16、17默认关闭偏向锁，JDK18之后移除偏向锁
 * 偏向锁（默认）
 * -XX:+UseBiasedLocking（开启偏向锁）：+表示开启  -表示关闭
 * -XX:BiasedLockingStartupDelay=0（偏向锁延迟启用 ）：0表示关闭 1表示启用
 * @author loriyuhv
 * @version  2025/9/24 8:52
 */
@Slf4j(topic = "c.TestBiased")
public class TestBiased {
    private final static Cat cat = new Cat();

    @Test
    public void test01(){
        // cat.hashCode(); // 会撤销对象的偏向锁
        log.debug(ClassLayout.parseInstance(cat).toPrintable()); // 无锁
        synchronized (cat) {
            log.debug(ClassLayout.parseInstance(cat).toPrintable()); // 轻量级锁
        }
        log.debug(ClassLayout.parseInstance(cat).toPrintable()); // 无锁
    }

    @Test
    public void test02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug(ClassLayout.parseInstance(cat).toPrintable());
            synchronized (cat) {
                log.debug(ClassLayout.parseInstance(cat).toPrintable());
            }
            log.debug(ClassLayout.parseInstance(cat).toPrintable());
            synchronized (TestBiased.class) {
                TestBiased.class.notify();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (TestBiased.class) {
                try {
                    TestBiased.class.wait();
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
            }
            log.debug(ClassLayout.parseInstance(cat).toPrintable());
            synchronized (cat) {
                log.debug(ClassLayout.parseInstance(cat).toPrintable());
            }
            log.debug(ClassLayout.parseInstance(cat).toPrintable());
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

class Cat {}
