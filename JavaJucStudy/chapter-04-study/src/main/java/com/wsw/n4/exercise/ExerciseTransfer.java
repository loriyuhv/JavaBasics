package com.wsw.n4.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 练习：多个共享变量的线程安全问题
 *
 * @author loriyuhv
 * @version 1.0 2025/11/7 13:46
 * @since 1.0
 */
@Slf4j(topic = "c.ExerciseTransfer")
public class ExerciseTransfer {
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAmount());
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        /* 查看转账2000次后的总金额 */
        log.debug("total: {}", (a.getMoney() + b.getMoney()));
    }

    /* 随机数1~5 */
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}

/* 账户 */
@Setter
@Getter
@AllArgsConstructor
@Slf4j(topic = "c.Account")
class Account {
    private int money;

    /* 转账 */
    public void transfer(Account target, int amount) {
        /* 效率不好，需要改进 */
        synchronized (Account.class) {
            if (money >= amount) {
                money -= amount;
                target.setMoney(target.getMoney() + amount);
            }
        }
    }
}
