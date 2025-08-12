package com.wsw07.constructor;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description TODO
 */
public class Animal {
    String name = "Animal";
    int age = 4;
    {
        this.name = "Jerry";
        this.age = 18;
    }

    public Animal() {
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
