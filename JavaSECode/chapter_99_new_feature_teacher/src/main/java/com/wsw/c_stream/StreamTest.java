package com.wsw.c_stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author loriyuhv
 * @version 1.0 2026/3/22 12:40
 * @since 1.0
 */
public class StreamTest {
    @Test
    public void test01() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // 传统方式：筛选偶数、平方、求和
        int sum = 0;
        for (Integer n : numbers) {
            if (n % 2 == 0) {
                sum += n * n;
            }
        }

        // Stream 方式：链式、可读、可并行
        Integer reduce = numbers
            .stream() // 获取顺序流
            .filter(n -> n % 2 == 0) // 筛选偶数
            .map(n -> n * n) // 映射为平方
            .reduce(0, Integer::sum); // 归纳求和
        System.out.println(reduce);
    }
}
