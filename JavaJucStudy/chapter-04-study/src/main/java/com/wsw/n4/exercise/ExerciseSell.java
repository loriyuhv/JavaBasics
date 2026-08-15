package com.wsw.n4.exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * 练习：一个共享变量的线程安全问题
 * 测试命令
 * for /L %n in (1,1,100) do java -cp ".;D:\Program Files\Maven\repo\org\projectlombok\lombok\1.18.38\lombok-1.18.38.jar;D:\Program Files\Maven\repo\ch\qos\logback\logback-classic\1.5.20\logback-classic-1.5.20.jar;D:\Program Files\Maven\repo\org\slf4j\slf4j-api\2.0.17\slf4j-api-2.0.17.jar;D:\Program Files\Maven\repo\ch\qos\logback\logback-core\1.5.20\logback-core-1.5.20.jar" com.exercise.ExerciseSell
 * @author loriyuhv
 * @version 1.0 2025/11/6 9:11
 * @since 1.0
 */
@Slf4j(topic = "c.ExerciseSell")
public class ExerciseSell {
    /* 随机数，Random为线程安全 */
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        /* 创建一个售票窗口，总票数为1000张。 */
        TicketWindow window = new TicketWindow(1000);

        /* 卖出的票数统计 */
        List<Integer> amountList = new Vector<>();

        /* 所有线程的集合 */
        List<Thread> threadList = getThreads(window, amountList);

        for (Thread thread : threadList) {
            thread.join();
        }

        /* 统计卖出的票数和剩余票数相同 */
        log.info("余票：{}", window.getCount());
        log.info("售卖票数：{}", amountList.stream().mapToInt(Integer::intValue).sum());
    }

    private static List<Thread> getThreads(TicketWindow window, List<Integer> amountList) {
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(() -> {
                /* 买票 */
                int amount = window.sell(randomAmount());
                try {
                    Thread.sleep(randomAmount() + 10);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                amountList.add(amount);
            });
            threadList.add(t);
            t.start();
        }
        return threadList;
    }

    /* 随机数1~5 */
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}

/* 售票窗口 */
@Slf4j(topic = "c.TicketWindow")
class TicketWindow {
    /* 余票数量 */
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    /* 售票：优化：加synchronized关键字 */
    public synchronized int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
    /* 获取余票数量：优化：加synchronized关键字 */
    public synchronized int getCount() {
        return count;
    }
}
