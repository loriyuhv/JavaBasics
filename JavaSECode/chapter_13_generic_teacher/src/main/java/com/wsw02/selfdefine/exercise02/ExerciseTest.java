package com.wsw02.selfdefine.exercise02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 练习2
 */
public class ExerciseTest {
    public static <E> void method1(E[] e, int a, int b) {
        E temp = e[a];
        e[a] = e[b];
        e[b] = temp;
    }

    public static <E> void method2(E[] e) {
        // 方式一
        // E temp = null;
        // for (int i = 0; i < e.length / 2; i++) {
        //     temp = e[i];
        //     e[i] = e[e.length - i - 1];
        //     e[e.length - i - 1] = temp;
        // }
        // 方式二
        for (int i = 0, j = e.length - 1; i < j; i++, j--) {
            E temp = e[i];
            e[i] = e[j];
            e[j] = temp;
        }
    }

    @Test
    public void test2() {
        String[] strings = new String[]{"Jerry", "Tom", "Aly", "James"};
        ExerciseTest.method1(strings, 2, 0);
        System.out.println(Arrays.toString(strings));
    }

    @Test
    public void test3() {
        Integer[] numbers = new Integer[]{1, 2, 3, 4, 5};
        ExerciseTest.method2(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}
