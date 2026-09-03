package com.wsw.concurrency.failstrategy;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author loriyuhv
 * @version 1.0 2026/9/3 15:31
 * @since 1.0
 */
public class FailSafeIteratorDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("next: " + next);
            if (next.equals("a")) {
                strings.remove(next);
            }
        }
        System.out.println("遍历结束，最终集合内容：" + strings);
    }
}
