package com.wsw04.other.dynamic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author loriyuhv
 * @date 2025/8/20
 * @description 体会反射的动态性
 */
public class ReflectTest {

    // 体会：静态性：
    public Person getInstance() {
        return new Person();
    }

    // 体会：反射的动态性
    public <T> T getInstance(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return (T) constructor.newInstance();
    }

    // 体会：反射的动态性
    // 举例1：
    public <T> T getInstance(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    @Test
    public void test1() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Person person1 = getInstance();
        System.out.println(person1);

        Person person2 = (Person) getInstance("com.wsw04.other.dynamic.Person");
        System.out.println(person2);

        Person person3 = getInstance(Person.class);
        System.out.println(person3);
    }

    // 体会：反射的动态性
    // 举例2
    public Object invoke(String className, String methodName) throws Exception {
        // 1. 创建全类名对应的运行时类的对象
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();

        // 2. 获取运行时类中的指定方法，并调用
        Method declaredMethod = clazz.getDeclaredMethod(methodName);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(instance);
    }

    @Test
    public void test2() throws Exception {
        Object invoke = invoke("com.wsw04.other.dynamic.Person", "show");
        System.out.println(invoke);
    }
}
