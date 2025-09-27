package com.wsw03.reflect_apply.apply03;

import com.wsw03.reflect_apply.data.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description
 *
 * @author loriyuhv
 * @version 2025/8/20
 */
public class ReflectTest {
    /**
     * 反射的应用3-1：调用指定的属性
     */
    // public int age = 1;
    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<Person> personClass = Person.class;
        // Person person = personClass.newInstance();
        Person person = personClass.getDeclaredConstructor().newInstance();
        // 1. 获取运行时类中指定名的属性
        Field ageField = personClass.getField("age");
        // 2. 设置并获取属性的值
        ageField.set(person, 24);
        System.out.println(ageField.get(person));
    }

    // private String name;
    @Test
    public void test2() throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<Person> personClass = Person.class;
        Person person = personClass.getDeclaredConstructor().newInstance();
        // 1. 通过Class实例调用getDeclaredField(String fieldName)，获取运行时类中指定名私有的属性
        Field nameField = personClass.getDeclaredField("name");
        // 2. 确保此属性是可访问的
        nameField.setAccessible(true);
        // 3. 通过Field类实例调用get(Object obj) 或set(Object obj)进行操作，设置并获取属性的值
        nameField.set(person, "Jerry");
        System.out.println(nameField.get(person));
    }

    // private static String info;
    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Person> personClass = Person.class;
        // 1. 通过Class实例调用getDeclaredField(String fieldName)，获取运行时类中指定名私有的属性
        Field infoField = personClass.getDeclaredField("info");
        // 2. 确保此属性是可访问的
        infoField.setAccessible(true);
        // 3. 通过Field类实例调用get(Object obj) 或set(Object obj)进行操作，设置并获取属性的值
        // infoField.set(personClass, "Hello reflect!");
        // System.out.println(infoField.get(personClass));
        // 仅限于类变量可以如下这种方式
        infoField.set(null, "Hello world!!!");
        System.out.println(infoField.get(null));
    }

    /**
     * 反射的应用3-2：调用指定的方法
     */
    // private String showNation(String nation, int age)
    @Test
    public void test4() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Person person = personClass.getDeclaredConstructor().newInstance();
        // 1. 通过Class实例调用getDeclaredMethod(String methodName, Class ...args)获取当前运行实例指定的方法
        Method showNationMethod = personClass.getDeclaredMethod("showNation", String.class, int.class);
        // 2. 确保此方法是可访问的
        showNationMethod.setAccessible(true);
        // 3. 通过Method类实例调用invoke(Object obj, Object ...objs)，即为对Method对应方法的调用
        // invoke()的返回值即为Method对应方法的返回值
        // 特别：如果Method对应的返回值类型为void，则invoke()返回值为null
        System.out.println(showNationMethod.invoke(person, "CHN", 18));
    }

    // public static void showInfo()
    @Test
    public void test5() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Method showInfo = personClass.getDeclaredMethod("showInfo");
        showInfo.setAccessible(true);
        Object invoke = showInfo.invoke(personClass);
        // Object invoke = showInfo.invoke(null);
        System.out.println(invoke);
    }

    /**
     * 3.3 调用指定的构造器
     */
    // private Person(String name, int age)
    @Test
    public void test6() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        // 1. 通过Class实例调用getDeclaredConstructor(Class ...args)获取指定参数类型的构造器
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class, int.class);
        // 2. 确保此方法是可访问的
        constructor.setAccessible(true);
        // 3. 通过Constructor实例调用newInstance(Object ...objs)返回一个运行时类的对象。
        Person person = constructor.newInstance("Jerry", 18);
        System.out.println(person);
    }

    // 使用Constructor替换原有的使用Class调用newInstance()的方式创建对象
    @Test
    public void test7() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Person person = personClass.getDeclaredConstructor().newInstance();
        System.out.println(person);
    }

}
