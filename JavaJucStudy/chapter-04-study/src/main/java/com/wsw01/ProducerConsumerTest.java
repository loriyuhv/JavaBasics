package com.wsw01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author loriyuhv
 * @date 2025/9/20 11:17
 * @description
 */

@Slf4j
public class ProducerConsumerTest {

    public static void main(String[] args) {
        MessageQueue<String> queue = new MessageQueue<>(2);

        // 创建三个生产者线程
        for (int i = 1; i <= 6; i++) {
            int id = i;
            new Thread(() -> {
                // 模拟生产者生产消息
                queue.put(new Message<>(id, "值" + id));
            }, "生产者" + i).start();
        }

        // 创建一个消费者线程
        for (int i = 1; i <= 1; i++) {
            new Thread(() -> {
                while (true) {
                    // try {
                    //     Thread.sleep(3000);
                    // } catch (InterruptedException e) {
                    //     throw new RuntimeException(e);
                    // }
                    Message<String> message = queue.take();
                    log.debug(message.toString());
                }

            }, "消费者" + i).start();
        }

    }
}
