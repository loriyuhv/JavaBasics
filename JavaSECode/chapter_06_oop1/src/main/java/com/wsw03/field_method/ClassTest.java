package com.wsw03.field_method;

import org.junit.jupiter.api.Test;

/**
 * @author loriyuhv
 * @date 2025/8/9
 * @description 温故而知新所有变量
 * 所有变量
 * 1. 成员变量
 *      1）实例变量
 *      2）类变量
 * 2. 局部变量
 */
public class ClassTest {
    public static void main(String[] args) {

    }
    // 测试实例变量 Person类
    @Test
    public void testInstance() {
        Person jack = new Person();
        jack.setName("jack");
        jack.setAge(18);
        System.out.println(jack.getName());
        System.out.println(jack.getAge());

        Person jerry = new Person();
        jerry.setName("jerry");
        jerry.setAge(22);
        System.out.println(jerry.getName());
        System.out.println(jerry.getAge());
    }

    // 测试类变量
    @Test
    public void testStaticMethod() {
        Animal jerry = new Animal();
        jerry.setName("jerry");
        jerry.setWeight(25);
        System.out.println(jerry.getName());
        System.out.println(jerry.getWeight());
        Animal.category = "人";
        System.out.println(Animal.category);

        Animal jack = new Animal();
        jack.setName("jack");
        System.out.println(jack.getName());
        System.out.println(Animal.category);
    }
}
