package com.wsw11.annotation;

import java.util.Date;

/**
 * @author loriyuhv
 * @date 2025/8/17
 * @description 注解使用
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Person person = new Student();
        person.walk();


        Date date = new Date();
        System.out.println(date);

        Person jerry =  new Person("Jerry");
        System.out.println(jerry);

        @SuppressWarnings("unused") int number;
    }
}

// @MyAnnotation(value="class")
class Person {
    String name;
    int age;

    @MyAnnotation(value="constructor")
    public Person() {}

    @Deprecated
    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println("人吃饭！");
    }

    public void walk() {
        System.out.println("人睡觉！");
    }
}

class Student extends Person {

    @Override
    public void eat() {
        System.out.println("学生吃饭！");
    }

    @Override
    // public void wa1k() { // 使用@Override注解可以避免这种情况
    public void walk() {
        System.out.println("学生睡觉！");
    }
}
