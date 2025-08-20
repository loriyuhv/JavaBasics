package com.wsw03.reflect_apply.apply02;

import com.wsw03.reflect_apply.data.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author loriyuhv
 * @date 2025/8/19
 * @description
 */
public class FieldsTest {
    @Test
    public void test1() {
        Class<Person> personClass = Person.class;
        // getFields()：获取到运行时类本身及其所有父类中声明为public权限的属性
        // Field[] personClassFields = personClass.getFields();
        // for (Field field : personClassFields) {
        //     System.out.println(field);
        // }

        // getDeclaredFields()：获取当前运行时类中声明的所有属性
        Field[] personClassDeclaredFields = personClass.getDeclaredFields();
        for (Field field : personClassDeclaredFields) {
            System.out.println(field);
        }
    }

    /**
     * 权限修饰符、变量类型、变量名
     */
    @Test
    public void test2() {
        Class<Person> personClass = Person.class;
        Field[] personClassDeclaredFields = personClass.getDeclaredFields();
        for (Field field : personClassDeclaredFields) {
            // 1. 权限修饰符号
            /*
             * 0x是十六进制
             * PUBLIC       = 0x0000 0001;  1   1
             * PRIVATE      = 0x0000 0002;  2   10
             * PROTECTED    = 0x0000 0004;  4   100
             * STATIC       = 0x0000 0008;  8   1000
             * FINAL        = 0x0000 0010;  16  10000
             * ...
             */
            int modifiers = field.getModifiers();
            System.out.print(modifiers + ":" + Modifier.toString(modifiers) + "\t");

            // 2. 数据类型
            Class<?> type = field.getType();
            System.out.print(type.getName() + "\t");
            // System.out.println(type.getSimpleName() + "\t");

            // 3. 变量名
            String fieldName = field.getName();
            System.out.print(fieldName);
            System.out.println();
        }

    }
}
