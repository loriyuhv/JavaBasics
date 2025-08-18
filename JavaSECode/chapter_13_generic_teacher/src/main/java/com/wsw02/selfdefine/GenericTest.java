package com.wsw02.selfdefine;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 泛型测试
 */
public class GenericTest {
    @Test
    public void test() {
        Person person = new Person();
        // Person<String> person = new Person<>(); // 错误，Person类不是泛型类
    }

    // 测试自定义泛型类
    @Test
    public void test2() {
        // 实例化时，可以明确指定类的泛型参数的具体类型
        Order<String> jerry = new Order<>("Jerry", 22013020);
        String name = jerry.getT();

        // 泛型参数在指定时，不可以使用基本数据类型，但是可以使用包装类替代。
        // Order<int> order1 = new Order<>(18, 2232);

        /*在实例化时，可以明确指定类的泛型参数的具体类型。一旦指定了泛型参数的类型，
        * 类中所有使用该泛型参数的位置将被替换为指定的具体类型。*/
        Order<Integer> order1 = new Order<>(18, 2232);
    }

    // 测试Order的子类SubOrder1
    @Test
    public void test3() {
        // 实例化SubOrder1
        SubOrder1 sub1 = new SubOrder1();
        // SubOrder1<Object> sub1 = new SubOrder1(); 等价吗？不等价 为什么，继承那节有解释。
        Object t = sub1.getT();
        // 因为SubOrder1不是泛型类，编译错误
        // SubOrder1<> sub1 = new SubOrder1<>();
    }

    // 测试Order的子类SubOrder2
    @Test
    public void test4() {
        SubOrder2 sub2 = new SubOrder2();
        Integer t = sub2.getT();
    }

    // 测试Order的子类SubOrder3
    @Test
    public void test5() {
        SubOrder3<Integer> sub3 = new SubOrder3<>();
        Integer t = sub3.getT();
        sub3.show(3);
    }

    // 测试Order的子类SubOrder4
    @Test
    public void test6() {
        SubOrder4<String> sub4 = new SubOrder4<>();
        Integer t = sub4.getT();
        String e = sub4.getE();
    }

    // 测试Order的子类SubOrder5
    @Test
    public void test7() {
        SubOrder5<String, Double> sub5 = new SubOrder5<>("IPhone7Plus", 18, 5999.99);
        String good = sub5.getT();
        Double price = sub5.getE();
    }

    // 测试泛型方法的使用
    @Test
    public void test8() {
        Order<Integer> order = new Order<>();
        String[] names = new String[]{"Jerry", "James", "Alan", "Tom"};
        List<String> strings = order.copyFromArrayToList(names);
        System.out.println(strings.getClass());
        for (String name : strings) {
            System.out.println(name);
        }
    }
}

