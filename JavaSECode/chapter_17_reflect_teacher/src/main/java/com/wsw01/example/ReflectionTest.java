package com.wsw01.example;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 反射测试
 */
public class ReflectionTest {
    /**
     * 使用反射之前可以执行的操作
     */
    @Test
    public void test1() {
        // 1. 创建Person类的实例
        // public Person()
        Person p1 = new Person();

        // 2. 调用属性
        // public int age;
        p1.age = 10;
        System.out.println(p1.age);

        // 3. 调用方法
        // public void show()
        p1.show();
    }

    /**
     * 使用反射完成上述的操作
     */
    @Test
    public void test2() throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        // 1. 创建Person类的实例
        // public Person()
        Class<Person> clazz = Person.class;
        Person p1 = clazz.newInstance();
        System.out.println(p1);

        // 2. 调用属性
        // public int age;
        Field age = clazz.getField("age");
        age.set(p1, 10);
        System.out.println(age.get(p1));

        // 3. 调用方法
        // public void show()
        Method showMethod = clazz.getMethod("show");
        showMethod.invoke(p1);
    }

    /**
     * 出了Person类之后，就不能直接调用Person类中声明的private权限修饰的结构（
     * 属性、方法和构造器）。但是，我们可以通过反射的方式，调用Person类中私有
     * 结构。
     */
    @Test
    public void test3() throws Exception {
        // 1. 调用私有的构造器，创建Person类的实例
        // private Person(String name, int age)
        Class<Person> clazz = Person.class;
        Constructor<Person> constructor = clazz.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        Person p1 = constructor.newInstance("Jerry", 18);
        System.out.println(p1);

        // 2. 调用私有属性
        // private String name;
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(p1, "Jerry");
        System.out.println(nameField.get(p1));

        // 3. 调用私有方法
        // private String showNation(String nation)
        Method showNationMethod = clazz.getDeclaredMethod("showNation", String.class);
        showNationMethod.setAccessible(true);
        String info = (String) showNationMethod.invoke(p1, "CHN");
        System.out.println(info);
    }
}
