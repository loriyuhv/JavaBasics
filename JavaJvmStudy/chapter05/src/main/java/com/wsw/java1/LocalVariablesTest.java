package com.wsw.java1;

import java.util.Date;

/**
 * @author loriyuhv
 * @since 1.0
 * @version 1.0 2025/9/23 8:01
 */
public class LocalVariablesTest {
    private int count = 0;

    public static void main(String[] args) {
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
        test.test1();
    }

    // 练习
    public static void testStatic() {
        LocalVariablesTest test = new LocalVariablesTest();
        Date date = new Date();
        int count = 10;
        System.out.println(count);
        // 因为this不存在当前方法的局部变量表中。
        // this.count++;
    }

    /* 关于Slot的理解 */
    public LocalVariablesTest() {
        this.count = 1;
    }

    public void test1() {
        Date date = new Date();
        String name1 = "wsw.com";
        String info = test2(date, name1);
        System.out.println(date + name1);
    }

    public String test2(Date dateP, String name2) {
        dateP = null;
        name2 = "Alan";
        double weight = 130.5; // 占用两个slot
        char gender = '男';
        return dateP + name2;
    }

    public void test3() {
        count++;
    }

    public void test4() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        // 变量c使用之前已经销毁的变量b占据的slot的位置
        int c = a + 1;
    }

    /**
     * 变量分类：
     *      按照数据类型分类：1）基本数据类型；2）引用数据类型
     *      按照声明位置分类：
     *          1）成员变量；使用前，都有默认初始值
     *              a）：类变量（静态变量）preparation阶段：类变量默认赋值 ==> initialization阶段：类变量显式赋值即静态代码块显式赋值
     *              b）：实例变量：随着对象的创建，会在堆空间中分配实例变量空间，并进行默认赋值。
     *          2）局部变量；在使用前，必须要进行显示赋值，否则，编译不通过。
     *              a）：方法局部变量
     *              b）：构造方法局部变量
     *              c）：代码块局部变量
     *          3）参数变量：
     *      按照作用域分类：
     *
     *
     */
}
