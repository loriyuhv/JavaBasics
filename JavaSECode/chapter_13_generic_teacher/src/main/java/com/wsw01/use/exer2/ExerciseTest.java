package com.wsw01.use.exer2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author loriyuhv
 * @date 2025/8/17
 * @description 题目
 */
public class ExerciseTest {

    @Test
    public void test() {
        // 1）创建一个ArrayList集合对象，并指定泛型为<Integer>
        List<Integer> integers = new ArrayList<>();
        // 2）添加5个[0,100)以内的随机整数到集合中
        for (int i = 1; i <= 5; i++) {
            int random = (int) (Math.random() * (100 - 0 + 1));
            integers.add(random);
        }
        // 3）使用foreach遍历5个整数
        for (Integer integer :integers) {
            System.out.println(integer);
        }
        System.out.println("-----------");
        // 4）使用集合的removeIf方法删除偶数，为Predicate接口指定泛型<Integer>
        integers.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        });

        // 5）再使用Iterator迭代器输出剩下的元素，为Iterator接口指定泛型<Integer>
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            System.out.println(integer);
        }
    }
}
