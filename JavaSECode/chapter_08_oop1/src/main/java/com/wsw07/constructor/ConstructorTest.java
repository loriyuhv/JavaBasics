package com.wsw07.constructor;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description TODO
 */
public class ConstructorTest {
    public static void main(String[] args) {
        // Animal animal = new Animal();
        // System.out.println(animal.name);
        // System.out.println(animal.age);
        Animal tom = new Animal("Tom", 22);
        System.out.println(tom.name);
        System.out.println(tom.age);
        tom.name = "Jerry";
        System.out.println(tom.name);
        System.out.println(tom.age);

    }
}
