package com.wsw03.more;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 通配符的使用
 */
public class GenericWildcardTest {
    // 测试：?通配符使用
    @Test
    public void test1() {
        List<?> list = null;
        List<Object> list1 = null;
        List<String> list2 = null;

        list = list1;
        list = list2;

        method(list1);
        method(list2);
    }

    public void method(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void test2() {
        List<?> list = null;
        List<String> list1 = new ArrayList<>();
        list1.add("AA");
        list = list1;

        // 读取数据（以集合为例说明）
        Object o = list.get(0);
        System.out.println(o);

        // 写入数据（以集合为例说明）
        // list.add("BB"); // 不可以
        // 特例：可以将null值写入集合中
        list.add(null);
    }

    // 测试：有限制条件的通配符使用

    /**
     * ? extends A
     * ? super A
     */
    @Test
    public void test3() {
        List<? extends Father> list = null;
        List<Object> list1 = null;
        List<Father> list2 = null;
        List<Son> list3 = null;

        // list = list1;
        list = list2;
        list = list3;
    }

    // 针对与? extends A格式的读写
    @Test
    public void test3_1() {
        List<? extends Father> list = null;
        List<Father> list1 = new ArrayList<>();
        list1.add(new Father());
        list = list1;
        // 读取数据：可以的
        Father father = list.get(0);
        System.out.println(father);

        // 写入数据：错误的
        list.add(null);
        // list.add(new Father());
        // list.add(new Son());
    }

    @Test
    public void test4() {
        List<? super Father> list = null;
        List<Object> list1 = null;
        List<Father> list2 = null;
        List<Son> list3 = null;

        list = list1;
        list = list2;
        // list = list3;
    }

    // 针对与? super A格式的读写
    @Test
    public void test4_1() {
        List<? super Father> list = null;
        List<Father> list1 = new ArrayList<>();
        list1.add(new Father());
        list = list1;
        // 读取数据: 可以的
        Object o = list.get(0);
        System.out.println(o);

        // 写入数据：可以将Father及其子类的对象
        list.add(null);
        // list.add(new Object());
        list.add(new Father());
        list.add(new Son());
    }
}
