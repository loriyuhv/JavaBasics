package com.wsw03.field_method.case01;

/**
 * @author loriyuhv
 * @date 2025/8/29 10:02
 * @description
 */
public class PersonTest {
    public static void main(String[] args) {
        Person p = new Person();
        p.name = "Jack";
        p.age = 24;
        p.gender = "male";

        p.eat();
        p.sleep(6);
        String info = p.interests("reading");
        System.out.println(info);
    }
}
