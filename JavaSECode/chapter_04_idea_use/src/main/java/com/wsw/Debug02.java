package com.wsw;

import java.util.HashMap;

/**
 * description
 *
 * @author loriyuhv
 * @version 2025/9/26 12:38
 * @since 1.0
 */
public class Debug02 {
    public static void main(String[] args) {
        // Son son = new Son();
        // son.test();

        // Father instance = new Son();
        // instance.test();
        //
        // Consumer consumer = new ConsumerImpl();
        // consumer.accept("Hello World");

        HashMap<String, Integer> students = new HashMap<>();
        students.put("Jerry", 18);
        students.put("Tom", 21);
        students.put("Jack", 22);
        Integer jerry = students.get("Jerry");
        System.out.println(jerry);
    }
}

class Father {
    public void test() {
        System.out.println("Father : test1");
        System.out.println("Father : test2");
    }
}

class Son extends Father {
    @Override
    public void test() {
        System.out.println("Son : test1");
        System.out.println("Son : test2");
    }
}

interface Consumer {
    void accept(String string);
}

class ConsumerImpl implements Consumer {
    @Override
    public void accept(String string) {
        System.out.println("ConsumerImpl: " + string);
    }
}
