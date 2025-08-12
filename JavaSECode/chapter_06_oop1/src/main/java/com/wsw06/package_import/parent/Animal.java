package com.wsw06.package_import.parent;

import lombok.Getter;
import lombok.Setter;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description 父类动物类
 */

@Setter
@Getter
public class Animal {
    // 名称
    private String name;
    // 重量
    double weight;
    // 年龄
    protected int age;
    // 颜色
    public String color;

    public void display() {
        System.out.println(this.name + " " + this.weight + " " + this.age + " " + this.color);
    }
}
