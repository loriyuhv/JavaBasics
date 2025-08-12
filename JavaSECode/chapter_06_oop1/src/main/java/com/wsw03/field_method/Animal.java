package com.wsw03.field_method;

import lombok.Data;

/**
 * @author loriyuhv
 * @date 2025/8/9
 * @description 动物类
 */
@Data
public class Animal {
    private String name;
    private double weight;
    static String category = "动物";

    public void running() {
        System.out.println("Animal is running!");
    }
}
