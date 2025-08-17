package com.wsw01.use;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author loriyuhv
 * @date 2025/8/17
 * @description 泛型
 */
public class CollectionMapTest {
    // 体会集合中使用泛型前的场景
    @Test
    public void test() {
        List list = new ArrayList();
        list.add(67);
        list.add(78);
        list.add(90);
        list.add(80);

        // 1. 问题1：类型不安全。因为add()的参数时Object类型，意味着任何类型的对象都可以添加成功
        // list.add("AA");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            // 2. 问题2：需要使用强转操作，繁琐。可能导致ClassCastException异常。
            Integer i = (Integer) iterator.next();
            int score = i;
            System.out.println(score);
        }
    }

    // 在集合使用泛型的例子
    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(67);
        list.add(78);
        list.add(90);
        list.add(80);

        // 编译报错，保证类型安全
        // list.add("Jerry");

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            // 避免强转报错
            Integer i = iterator.next();
            int score = i;
            System.out.println(score);
        }
    }

    // 泛型在Map中使用的例子
    @Test
    public void test3() {
        // HashMap<Integer, String> students = new HashMap<Integer, String>();
        // JDK7的新特性
        HashMap<Integer, String> students = new HashMap<>(); // 类型推断

        students.put(1, "Jerry");
        students.put(2, "Tom");

        // Set<Map.Entry<Integer, String>> entries = students.entrySet();
        // Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();

        // JDK10新特性
        var entrySet = students.entrySet();
        var iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            Integer key = next.getKey();
            String value = next.getValue();
            System.out.println(key + ":" + value);
        }

    }
}
