package com.wsw01._this;

/**
 * @author loriyuhv
 * @date 2025/8/11
 * @description 学生类
 */
public class Student {
    String name;
    int age;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int age) {
        this(name);
        this.age = age;
    }
}
