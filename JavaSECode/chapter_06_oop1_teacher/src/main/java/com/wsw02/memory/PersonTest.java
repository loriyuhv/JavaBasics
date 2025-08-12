package com.wsw02.memory;

/**
 * @author loriyuhv
 * @date 2025/8/9
 * @description Person类的测试类
 */
public class PersonTest {
    public static void main(String[] args) {
        // 创建对象、类的实例化
        Person person = new Person();

        // 通过对象调用属性和方法
        person.name = "Jerry";
        person.age = 18;

        System.out.println(person.name);
        System.out.println(person.age);

        person.eat();
        person.sleep(8);

        // 创建第二个对象
        Person person1 = new Person();
        person1.name = "Jack";
        person1.age = 22;
        System.out.println(person1.name);
        System.out.println(person1.age);
        person1.eat();
        person1.sleep(8);
    }
}
