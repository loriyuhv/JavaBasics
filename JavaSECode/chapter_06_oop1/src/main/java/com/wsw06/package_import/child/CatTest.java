package com.wsw06.package_import.child;

import com.wsw06.package_import.parent.Animal;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description Cat类测试
 */
public class CatTest {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal.color);
    }
}
