package com.wsw11.annotation;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author loriyuhv
 * @date 2025/8/17
 * @description Junit测试数据
 */
public class JunitTest {
    @Test
    public void testHello() {
        System.out.println("Hello junit2!!!");
    }

    @Test
    public void useStudent() {
        Person jerry = new Student();
        jerry.walk();
    }

    @Test
    public void testScanner() {
        String string = new Scanner(System.in).nextLine();
        System.out.println(string);
    }
}
