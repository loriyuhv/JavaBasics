package com.wsw.test.test20.test2006;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/17 12:04
 * @since 1.0
 */
@Slf4j(topic = "c.Test2006")
public class Test2006 {
    public static void main(String[] args) throws InterruptedException {
        Mailboxes<Letter> mailboxes = new Mailboxes<>();
        // 启动16个收件人
        new Thread(() -> {
            for (int i = 1; i < 16; i++) {
                new People<>("p" + i, mailboxes).start();
            }
        }).start();

        Thread.sleep(100);

        // 获取快照id集合
        Set<Integer> ids = mailboxes.getIds();
        for (Integer id : ids) {
            // 投递有效信件
            Letter letter = new Letter(id, "编号" + id + " 的信件内容");
            new Postman<>(id, "t" + id, letter, mailboxes).start();
        }
    }
}

@Data
@AllArgsConstructor
class Letter {
    private int id;
    private String message;
}

@Slf4j(topic = "c.People")
class People<T> extends Thread {
    private final String name;
    private final Mailboxes<T> mailboxes;

    public People(String name, Mailboxes<T> mailboxes) {
        super(name);
        this.name = name;
        this.mailboxes = mailboxes;
    }

    @Override
    public void run() {
        // 创建自己的信箱
        GuardedObject<T> go = mailboxes.createGuardedObject();
        log.debug("{} 创建信箱，id {}, 开始等待结果", name, go.getId());

        // 最多等待3秒
        T result = go.get(100000);
        if (result != null) {
            log.debug("{} 收到信件：{}", name, result);
        } else {
            log.debug("{} 等待超时，未收到信件", name);
        }
        GuardedObject<T> removeGo = mailboxes.removeGuardedObject(go.getId());
        log.debug("{} 号信箱被移除了", removeGo.getId());
    }
}

@Slf4j(topic = "c.Postman")
class Postman<T> extends Thread {
    private final int id;
    private final String name;
    private final T mail;
    private final Mailboxes<T> mailboxes;

    public Postman(int id, String name, T mail, Mailboxes<T> mailboxes) {
        super(name);
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.mailboxes = mailboxes;
    }

    @Override
    public void run() {
        log.debug("{} 开始投递，投递信箱编号是 {}", name, id);
        GuardedObject<T> go = mailboxes.getGuardedObject(id);
        if (go == null) {
            log.warn("{} 投递失败：不存在id={}的信箱", name, id);
            return;
        }
        while (true) {
            int rand = new Random().nextInt(100000000);
            if (rand == 88888888) {
                break;
            }
        }
        go.complete(mail);
        log.debug("{} 投递完成 id={}", name, id);
    }
}

@Slf4j(topic = "c.Mailboxes")
class Mailboxes<T> {
    private int id = 1;
    private final Map<Integer, GuardedObject<T>> mailboxes = new ConcurrentHashMap<>();

    private synchronized int generateId() {
        return this.id++;
    }

    public GuardedObject<T> createGuardedObject() {
        GuardedObject<T> go = new GuardedObject<>(generateId());
        mailboxes.put(go.getId(), go);
        return go;
    }

    public Set<Integer> getIds() {
        return new HashSet<>(mailboxes.keySet());
    }

    public GuardedObject<T> getGuardedObject(int id) {
        return mailboxes.get(id);
    }

    // 收到/超时后清理信箱，防止map内存持续上涨
    public GuardedObject<T> removeGuardedObject(int id) {
        return mailboxes.remove(id);
    }
}

@Getter
@Slf4j(topic = "c.GuardedObject")
class GuardedObject<T> {
    private final int id;
    private T result;

    public GuardedObject(int id) {
        this.id = id;
    }

    public synchronized T get(long timeout) {
        long start = System.currentTimeMillis();
        long passedTime = 0;
        while (result == null) {
            long waitTime = timeout - passedTime;
            try {
                if (waitTime <= 0) {
                    break;
                }

                this.wait(waitTime);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
                Thread.currentThread().interrupt(); // 恢复中断标记
                break;
            }
            passedTime = System.currentTimeMillis() - start;
        }
        return result;
    }

    public synchronized void complete(T result) {
        this.result = result;
        this.notify();
    }
}
