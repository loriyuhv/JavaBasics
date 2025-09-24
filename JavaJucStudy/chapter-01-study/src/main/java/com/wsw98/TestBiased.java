package com.wsw98;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author loriyuhv
 * @date 2025/9/24 8:52
 * @description 偏向锁（默认）
 * -XX:+UseBiasedLocking（开启偏向锁）：+表示开启  -表示关闭
 * -XX:BiasedLockingStartupDelay=0（偏向锁延迟启用 ）：0表示关闭 1表示启用
 */
@Slf4j
public class TestBiased {
    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat();
        // cat.hashCode(); // 会禁用对象的偏向锁
        // log.debug(ClassLayout.parseInstance(cat).toPrintable());
        // synchronized (cat) {
        //     log.debug(ClassLayout.parseInstance(cat).toPrintable());
        // }
        // log.debug(ClassLayout.parseInstance(cat).toPrintable());

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
    }
}

class Cat {

}
