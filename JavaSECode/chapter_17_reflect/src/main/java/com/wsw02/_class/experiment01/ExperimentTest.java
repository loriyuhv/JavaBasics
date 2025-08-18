package com.wsw02._class.experiment01;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 实验：关于多态中反射的理解
 */
public class ExperimentTest {
    /**
     * 代码：Person jerry = new Student();
     * 实验：多态状态下，通过对象.getClass()的出来的Class实例实际是子类还是父类
     * 结果：子类
     * @throws Exception 异常类
     */
    @Test
    public void test1() throws Exception {
        Person jerry = new Student();
        Class<? extends Person> studentClass = jerry.getClass();

        // 查看运行时类是Person类还是Student类：运行时类是Student类
        Field[] declaredFields = studentClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }
        System.out.println("------------");

        // 多态：编译时看左边（父类方法）运行时看右边（子类方法）
        // 父类没有setStuId()方法，利用反射获取setStuId()给jerry赋值20197360
        Method setStuId = studentClass.getDeclaredMethod("setStuId", long.class);
        setStuId.setAccessible(true);
        setStuId.invoke(jerry, 20197360);
        // 利用多态，打印Jerry的学号
        jerry.displayStuId();
    }

    /**
     * 获取Class实例，实例类型的泛型参数为什么是? extends Person
     */
    @Test
    public void test2() {
        // 场景1
        Person person1 = new Person();
        Class<? extends Person> personClass = person1.getClass();
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }

        // 场景2
        Person person2 = new Student();
        Class<? extends Person> studentClass = person2.getClass();
        Field[] declaredField = studentClass.getDeclaredFields();
        for (Field field : declaredField) {
            System.out.println(field.getName());
        }
    }
}
