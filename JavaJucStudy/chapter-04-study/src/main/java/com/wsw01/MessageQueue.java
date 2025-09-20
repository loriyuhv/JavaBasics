package com.wsw01;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author loriyuhv
 * @date 2025/9/20 10:51
 * @description 设计模式——生产者消费者
 */
// 消息队列类，Java线程之间通信
@Slf4j(topic = "MessageQueue")
@AllArgsConstructor
public class MessageQueue<T> {
    // Java的队列：通过linkedList实现
    // 消息的队列集合
    private final LinkedList<Message<T>> list = new LinkedList<>();
    // 消息队列容量
    private int capacity;


    // 获取消息
    public Message<T> take() {
        // 检查队列是否为空
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    log.debug("队列为空，消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 从队列头部获取消息并返回
            Message<T> message = list.removeFirst();
            log.debug("已经消费消息 {}", message);
            // list.notifyAll(); // 唤醒所有线程
            list.notify(); // 随机唤醒一个线程
            return message;
        }
    }

    // 存入消息
    public void put(Message<T> message) {
        synchronized (list) {
            // 检查队列是否已满
            while (list.size() == capacity) {
                try {
                    log.debug("队列已满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 将消息加入队列尾部
            list.addLast(message);
            log.debug("已生产消息 {}", message);
            list.notifyAll();
        }
    }
}
