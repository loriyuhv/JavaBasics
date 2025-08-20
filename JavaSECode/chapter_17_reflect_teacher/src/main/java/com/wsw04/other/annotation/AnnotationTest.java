package com.wsw04.other.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author loriyuhv
 * @date 2025/8/20
 * @description
 */
public class AnnotationTest {
    // 获取类声明上的注解
    @Test
    public void test1() {
        Class<Customer> customerClass = Customer.class;
        Table annotation = customerClass.getDeclaredAnnotation(Table.class);
        System.out.println(annotation.value());
    }

    // 获取属性声明上的注解
    /*
    * @Column(columnName = "customer_name", columnType = "varchar(15)")
    * private String name;
     */
    @Test
    public void test2() throws NoSuchFieldException {
        Class<Customer> customerClass = Customer.class;
        Field nameField = customerClass.getDeclaredField("name");
        nameField.setAccessible(true);
        Column annotation = nameField.getDeclaredAnnotation(Column.class);
        System.out.println(annotation.columnName() + ":" + annotation.columnType());
    }
}
