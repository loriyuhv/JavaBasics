package com.test;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * 同步模式之保护性暂停
 *
 * @author loriyuhv
 * @version 1.0 2025/9/25 6:47
 * @since 1.0
 */
@Slf4j
public class Test20 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }

        // Thread.sleep(1000);
        // for (Integer id : Mailboxes.getIds()) {
        //     new Postman(id, "mail" + id).start();
        // }
    }
}

// 居民类
@Slf4j
class People extends Thread {
    @Override
    public void run() {
        // 收信
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        log.debug("开始收信，id：{}", guardedObject.getId());
        Object mail = guardedObject.getResponse(5000);
        if (mail != null) {
            log.debug("收到信了，id：{}，内容：{}", guardedObject.getId(), mail);
        } else {
            log.debug("没收到信！");
        }

    }
}

// 邮递员类
@Slf4j
class Postman extends Thread {
    private int id;
    private String mail;

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
    private final static Map<Integer, GuardedObject> mailboxes = new Hashtable<>();
    private static int id = 1;

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
        // mailboxes.get(id); // 根据id(键)返回值，但键还在map中
        return mailboxes.remove(id); // 根据id(键)返回值，但键不在map中
    }
}
// 增加超时效果
@Slf4j
@Getter
class GuardedObject {
    // 表示GuardedObject
    private int id;
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
    public Object getResponse(long timeout) {
        synchronized (this) {
            // 开始时间
            long start = System.currentTimeMillis();
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
                passedTime = System.currentTimeMillis() - start;
            }
        }
        return response;
    }

    // 产生结果
    public void complete(Object response) {
        synchronized (this) {
            // 给结果成员变量赋值
            this.response = response;
            this.notify();
        }
    }
}
