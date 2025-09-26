package com.wsw02.memory;

/**
 * @author loriyuhv
 * @since 1.0
 * @version 2025/8/28 7:44
 */
public class PersonTest {
    public static void main(String[] args) {
        Person jerry = new Person();
        jerry.name = "Jerry";
        // jerry.age = 18;
        jerry.gender = "male";

        Person jack = new Person();
        jack.name = "Jack";
        jack.age = 19;
        jack.gender = "male";

        jerry.age = 26;
        System.out.println(jack.age);

        Person alice;
        alice = jerry;
        alice.age = 28;
        System.out.println(alice.age);
        System.out.println(jerry.age);
    }
}
