package com.wsw.test.test21.test2102;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 优化，带超时的等待
 * @author loriyuhv
 * @version 1.0 2026/8/18 11:14
 * @since 1.0
 */
@Slf4j(topic = "c.Test2102")
public class Test2102 {
    public static void main(String[] args) {
        MessageQueue<String> messageQueue = new MessageQueue<>(2);

        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> messageQueue.offer(new Message<>(id, "消息" + id)), "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                Message<String> message = messageQueue.poll(2000);
                if (message == null) {
                    break;
                }
                log.debug("消费者：{}", message);
            }
        }, "消费者").start();
    }
}

@Slf4j(topic = "c.MessageQueue")
class MessageQueue<T> {
    private final LinkedList<Message<T>> list = new LinkedList<>();
    private final int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message<T> poll(long timeout) {
        synchronized (list) {
            long start = System.currentTimeMillis();
            long passedTime = 0;
            while (list.isEmpty()) {
                long waitTime = timeout - passedTime;
                try {
                    if (waitTime <= 0) {
                        return null;
                    }
                    list.wait(waitTime);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                passedTime = System.currentTimeMillis() - start;
            }
            Message<T> message = list.removeFirst();
            list.notifyAll();
            return message;
        }
    }

    public void offer(Message<T> message) {
        synchronized (list) {
            while (list.size() == capacity) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            list.addLast(message);
            log.debug("生产者offer{}", message);
            list.notifyAll();
        }

    }
}

@Setter
@ToString
@AllArgsConstructor
@Slf4j(topic = "c.Message")
final class Message<T> {
    private int id;
    private T data;
}
