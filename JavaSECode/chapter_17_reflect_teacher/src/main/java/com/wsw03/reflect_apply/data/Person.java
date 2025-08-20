package com.wsw03.reflect_apply.data;

import lombok.Data;

/**
 * @author loriyuhv
 * @date 2025/8/19
 * @description Person
 */

@MyAnnotation("t_persons")
public class Person extends Creature<String> implements Comparable<Person> {
    private String name;
    public int age = 1;
    @MyAnnotation("info")
    private static String info;

    public Person() {
        System.out.println("Person() ...");
    }

    protected Person(int age) {
        this.age = age;
    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void show() throws RuntimeException, ClassNotFoundException {
        System.out.println("你好，我是一个Person。");
    }

    @MyAnnotation(value="show_nation")
    private String showNation(String nation, int age) {
        System.out.println("showNation ...");
        return "我的国籍是：" + nation + ", 生活了" + age + "年了。";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }

    public static void showInfo() {
        System.out.println("我是一个人。");
    }
}
