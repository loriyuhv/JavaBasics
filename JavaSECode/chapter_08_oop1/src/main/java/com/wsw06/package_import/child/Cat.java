package com.wsw06.package_import.child;

import com.wsw06.package_import.parent.Animal;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description TODO
 */
public class Cat extends Animal {
    public void run() {
        super.age = 18;
        super.color = "red";
    }
}
