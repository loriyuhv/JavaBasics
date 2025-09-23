package com.wsw.java2;

/**
 * @author loriyuhv
 * @date 2025/9/23 14:11
 * @description 早期绑定和晚期绑定示例
 */
class Animal {
    public void eat() {
        System.out.println("动物进食");
    }
}

interface Hunt {
    void hunt();
}

class Dog extends Animal implements Hunt {
    @Override
    public void hunt() {
        System.out.println("捕食耗子，多管闲事");
    }

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }
}

class Cat extends Animal implements Hunt {
    public Cat() {
        super(); // 早期绑定
    }

    public Cat(String name) {
        this(); // 早期绑定
    }

    @Override
    public void eat() {
        super.eat(); // 早期绑定
        System.out.println("猫吃鱼");
    }

    @Override
    public void hunt() {
        System.out.println("捕食耗子，天经地义");
    }
}

public class AnimalTest {
    public void showAnimal(Animal animal) {
        animal.eat(); // 表现为：晚期绑定
    }

    public void showHunt (Hunt hunt) {
        hunt.hunt(); // 表现为：晚期绑定
    }
}