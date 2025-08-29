package com.wsw03.field_method.method;

/**
 * @author loriyuhv
 * @date 2025/8/29 7:06
 * @description
 */
public class MethodTest {
    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Jerry";
        person.age = 18;
        person.gender = 'm';

        person.eat();
        person.sleep(6);
        String info = person.interests("reading");
        System.out.println(info);
    }
}

class Person {
    // 属性
    String name;
    int age;
    char gender;

    public void eat() {
        System.out.println("人吃饭！");
    }

    public void sleep(int hour) {
        System.out.println("人至少每天睡眠" + hour + "小时。");
    }

    public String interests(String hobby) {
        return "我的爱好是" + hobby;
    }
}
