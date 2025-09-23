package com.wsw.java2;

/**
 * @author loriyuhv
 * @date 2025/9/23 16:06
 * @description invokedynamic
 */
@FunctionalInterface
interface Calculator {
    int compute(int a, int b);
}

public class InvokeDynamicTest {
    public static void main(String[] args) {
        // Lambada实现函数式接口
        Calculator product = (x, y) -> x * y;
        Calculator different = (x, y) -> x - y;

        int product1 = product.compute(1, 2);
        int different1 = different.compute(4, 3);
        System.out.println("product value = " + product1);
        System.out.println("different value = " + different1);
    }
}
