package com.wsw.test.test20.test2003;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/16 16:04
 * @since 1.0
 */
@Slf4j(topic = "c.Test2003")
public class Test2003 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            new People().start();
        }

        Thread.sleep(2000);

        // ConcurrentModificationException 原因就是遍历时，其他线程修改了HashTable集合
        // for (Integer id : Mailboxes.getIds()) {
        //     new Postman(id, id.toString()).start();
        // }
        // 优化
        HashSet<Integer> ids = new HashSet<>(Mailboxes.getIds());
        for (Integer id : ids) {
            new Postman(id, id.toString()).start();
        }


    }
}

// 居民类
@Slf4j(topic = "c.People")
class People extends Thread {
    @Override
    public void run() {
        // 收信
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        log.debug("开始收信，id：{}", guardedObject.getId());
        Object mail = guardedObject.get(50000);
        if (mail != null) {
            log.debug("收到信了，id：{}，内容：{}", guardedObject.getId(), mail);
        } else {
            log.debug("没收到信！");
        }

    }
}

// 邮递员类
@Slf4j(topic = "c.Postman")
class Postman extends Thread {
    private final int id;
    private final String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.getGuardedObject(id);
        log.debug("开始送信 id:{}，内容:{}", id, mail);
        guardedObject.complete(mail);
    }
}

// 邮箱类
class Mailboxes {
    private static int id = 1;
    private final static Map<Integer, GuardedObject> mailboxes = new Hashtable<>();

    // 产生唯一的Id
    public static synchronized int generateId() {
        return id++;
    }

    // 产生GuardedObject对象
    public static GuardedObject createGuardedObject() {
        GuardedObject guardedObject = new GuardedObject(generateId());
        mailboxes.put(guardedObject.getId(), guardedObject);
        return guardedObject;
    }

    public static Set<Integer> getIds() {
        return mailboxes.keySet();
    }

    // 获取GuardedObject对象
    public static GuardedObject getGuardedObject(int id) {
        // return mailboxes.get(id); // 根据id(键)返回值，但键还在map中
        return mailboxes.remove(id); // 根据id(键)返回值，但键不在map中
    }
}

// 增加超时效果
@Getter
@Slf4j(topic = "c.GuardedObject")
class GuardedObject {
    // 表示GuardedObject
    private final int id;
    // 结果
    private Object response;

    public GuardedObject(int id) {
        this.id = id;
    }

    /**
     * 获取结果
     * @param timeout 等待时间
     * @return 结果
     */
    public synchronized Object get(long timeout) {
        // 开始时间
        long begin = System.currentTimeMillis();
        // 经历时间
        long passedTime = 0;
        // 没有结果
        while (response == null) {
            // 等待时间
            long waitTime = timeout -passedTime;
            // 经历的时间超过了最大等待时间，退出循环
            if (waitTime <= 0) {
                break;
            }
            try {
                this.wait(waitTime);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            // 求得经历时间
            passedTime = System.currentTimeMillis() - begin;
        }
        return response;
    }

    // 产生结果
    public synchronized void complete(Object response) {
        // 给结果成员变量赋值
        this.response = response;
        this.notifyAll();
    }
}
