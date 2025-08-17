package com.wsw01.use.exer1;

import lombok.Getter;
import lombok.Setter;

/**
 * @author loriyuhv
 * @date 2025/8/17
 * @description 定义一个Employee类
 *     该类包括：private成员变量name，age，birthday，其中birthday为MyDate类的对象；
 *     并为每一个属性定义getter、setter方法。
 *     并重写toString方法输出name，age，birthday。
 */
@Setter
@Getter
public class Employee implements Comparable<Employee> {
    private String name;
    private int age;
    private MyDate birthday;

    public Employee() {
    }

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
    // 安装name从低到高排序
    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }
}
