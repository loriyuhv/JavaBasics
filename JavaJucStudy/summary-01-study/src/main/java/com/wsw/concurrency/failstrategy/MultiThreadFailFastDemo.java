package com.wsw.concurrency.failstrategy;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Fail‑Fast 多线程并发演示
 * 遍历线程、修改线程并发操作ArrayList，触发 ConcurrentModificationException
 *
 * @author loriyuhv
 * @version 1.0 2026/9/3 15:40
 * @since 1.0
 */
@Slf4j(topic = "MultiThreadFailFastDemo")
public class MultiThreadFailFastDemo {
    public static void main(String[] args) {
        // Fail-Fast: 遍历线程、修改线程并发操作ArrayList，触发 ConcurrentModificationException
        // List<String> list = new ArrayList<>();
        // Fail-Safe：修改线程删除元素，遍历线程不受影响，继续遍历快照旧数据，不会抛异常
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("任务A");
        list.add("任务B");
        list.add("任务C");
        list.add("任务D");

        new Thread(() -> {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                log.info("t1: {}", next);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            log.info("t1 iterator finished.");
        }, "t1").start();

        // 修改线程：延迟1秒，删除元素
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                log.info("t2:删除任务C");
                list.remove("任务C");
                log.info("current list : {}", list);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "t2").start();
    }
}
