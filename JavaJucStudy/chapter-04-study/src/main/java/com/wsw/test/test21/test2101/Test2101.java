package com.wsw.test.test21.test2101;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 设计模式——生产者消费者
 * @author loriyuhv
 * @version 1.0 2026/8/18 10:24
 * @since 1.0
 */
@Slf4j(topic = "c.Test2101")
public class Test2101 {
    public static void main(String[] args) {
        MessageQueue<String> messageQueue = new MessageQueue<>(2);

        for (int i = 0; i < 3; i++) {
            int j = i;
            new Thread(() -> messageQueue.put(new Message<>(j, "消息" + j)), "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                // Thread.sleep(1000); // 测试生产者等待
                Message<String> message = messageQueue.take();
                log.debug("已消费消息{}", message);
            }
        }, "消费者").start();
    }
}


/**
 * 消息队列，Java线程之间通信
 */
@Slf4j(topic = "c.MessageQueue")
class MessageQueue<T> {
    /**
     * 消息队列集合
     */
    private final LinkedList<Message<T>> list =new LinkedList<>();
    /**
     * 队列容量
     */
    private final int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取消息
     * @return 消息对象
     */
    public Message<T> take() {
        synchronized (list) {
            // 检查队列是否为空
            while (list.isEmpty()) {
                try {
                    log.debug("队列为空, 消费者线程等待");
                    list.wait();
                }  catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            // 消息从队列头部取出
            Message<T> message = list.removeFirst();
            list.notifyAll();
            return message;
        }
    }

    /**
     * 存入消息
     */
    public void put(Message<T> message) {
        synchronized (list) {
            while (list.size() == capacity) {
                try {
                    log.debug("队列已满, 生产者线程等待");
                    list.wait();
                }  catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            // 将消息加入队列尾部
            list.addLast(message);
            log.debug("已生产消息{}", message);
            list.notifyAll();
        }
    }

}

/**
 * 不加@Setter：意味着他不可被操作
 * final: 意味着没有子类
 * @param <T>
 */
@Getter
@ToString
@AllArgsConstructor
final class Message<T> {
    private int id;
    private T data;
}
