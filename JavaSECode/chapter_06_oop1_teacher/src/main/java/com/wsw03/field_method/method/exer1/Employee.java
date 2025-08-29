package com.wsw03.field_method.method.exer1;

/**
 * @author loriyuhv
 * @date 2025/8/29 6:47
 * @description
 */
public class Employee {
    int id;
    String name;
    int age;
    double salary;
    
    public void display() {
        System.out.println("员工1的编号：" + this.id + "，姓名：" + this.name + "，年龄：" + this.age + "，薪资：" + this.salary);
    }
}
