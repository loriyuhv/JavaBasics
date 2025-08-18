package com.wsw03.more;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description
 */
public class GenericTest {
    @Test
    public void test1() {
        // 1.
        Object obj = null;
        String str = "AA";

        obj = str; // 基于继承性的多态使用

        // 2.
        Object[] arr = null;
        String[] arr1 = null;
        arr = arr1; // 基于继承性的多态使用
    }

    /**
     * 类SuperA是类A的父类，则G<superA> 与G<A>的关系：是并列的两个类，
     * 没有任何子父类的关系。
     */
    @Test
    public void test2() {
        ArrayList<Object> list1 = null;
        ArrayList<String> list2 = null;
        // list1 = list2; // 不可以

        //
        /*
        * 反证法
        * ArrayList<Object> list1 = null;
        * ArrayList<String> list2 = new ArrayList<>();
        * 假设 list1 = list2是可以的
        * list2.add("AA");
        *
        * list1.add(123);
        *
        * String str = list2.get(1); // 相当于取出的123赋值给str，错误的
        *
        *
         */
    }

    @Test
    public void test3() {
        Person<Object> person = null;
        Person<String> person1 = null;
        // person = person1; // 不可以
    }

    @Test
    public void test4() {
        ArrayList<Object> list1 = null;
        ArrayList<String> list2 = null;
        method(list1);
        // method(list2); // 报错，因为ArrayList<Object> 与ArrayList<String>没有任何子父类的关系
    }

    public void method(ArrayList<Object> List) {}

    /*
    * 类SuperA是类A的父类或接口，SuperA(G)与A<G>的关系：
    *   SuperA(G)与A<G>有继承或实现的关系。即A<G>可以赋值给
    *   SuperA(G)类型的引用（或变量）
     */
    @Test
    public void test5() {
        List<String> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();
        list1 = list2;

        list1.add("AA");
        System.out.println(list2.get(0));

        method1(list2);
    }

    public void method1(List<String> list) {}


}
