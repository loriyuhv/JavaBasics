package com.wsw.concurrency.failstrategy;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Fail-Fast 和Fail-Safe：一种多线程并发操作集合的失败处理机制
 * Fail-Fast：快速失败，在集合遍历过程中，一旦发现集合中的数据被修改了，会立刻
 * 抛出ConcurrentModificationException
 *
 * @author loriyuhv
 * @version 1.0 2026/9/3 15:22
 * @since 1.0
 */
public class FailFastIteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("next:" + next);
            if ("a".equals(next)) {
                list.remove(next);
            }
        }
    }
}
