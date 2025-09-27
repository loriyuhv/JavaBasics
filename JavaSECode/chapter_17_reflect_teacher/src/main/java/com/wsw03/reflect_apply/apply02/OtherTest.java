package com.wsw03.reflect_apply.apply02;

import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * description （熟悉）获取运行时类的内部结构2：父类、接口、包、带泛型的类、父类的泛型等
 *
 * @author loriyuhv
 * @version 2025/8/20
 */
public class OtherTest {
    // 1. 获取运行时类的父类
    @Test
    public void test1() throws ClassNotFoundException {
        Class<?> personClass = Class.forName("com.wsw03.reflect_apply.data.Person");
        Class<?> personClassSuperclass = personClass.getSuperclass();
        System.out.println(personClassSuperclass.getName());
    }
    // 2. 获取运行时类实现的接口
    @Test
    public void test2() throws ClassNotFoundException {
        Class<?> personClass = Class.forName("com.wsw03.reflect_apply.data.Person");
        Class<?>[] interfaces = personClass.getInterfaces();
        for (Class<?> i : interfaces) {
            System.out.println(i);
        }
    }

    // 3. 获取运行时所在的包
    @Test
    public void test3() throws ClassNotFoundException {
        Class<?> personClass = Class.forName("com.wsw03.reflect_apply.data.Person");
        Package aPackage = personClass.getPackage();
        System.out.println(aPackage);
        // System.out.println(aPackage.getName());
    }

    // 4. 获取运行时带泛型的父类
    @Test
    public void test4() throws ClassNotFoundException {
        Class<?> personClass = Class.forName("com.wsw03.reflect_apply.data.Person");
        Type personClassGenericSuperclass = personClass.getGenericSuperclass();
        System.out.println(personClassGenericSuperclass);
    }

    // 5. 获取运行时带泛型的接口
    @Test
    public void test4_2() throws ClassNotFoundException {
        Class<?> personClass = Class.forName("com.wsw03.reflect_apply.data.Person");
        Type[] genericInterfaces = personClass.getGenericInterfaces();
        for (Type i : genericInterfaces) {
            System.out.println(i);
        }
    }

    // 5. 获取运行时类的父类的泛型（难）

    /**
     * 平时写的代码：
     * 类型1：业务逻辑代码（多关注）
     * 类型2：算法逻辑代码（多积累）
     * @throws ClassNotFoundException
     */
    @Test
    public void test5() throws ClassNotFoundException {
        Class<?> personClass = Class.forName("com.wsw03.reflect_apply.data.Person");
        // 获取带泛型的父类（Type是一个接口，Class实现了此接口
        Type personClassGenericSuperclass = personClass.getGenericSuperclass();
        // 如果父类是带泛型的，则可以强转为ParameterizedType
        ParameterizedType paramType = (ParameterizedType) personClassGenericSuperclass;
        // 调用getActualTypeArguments()获取泛型参数，结果是一个数组，因为可能有多个泛型参数
        Type[] paramTypeArgs = paramType.getActualTypeArguments();
        // 获取泛型参数的名称
        for (Type i : paramTypeArgs) {
            System.out.println(i.getTypeName());
        }
    }
}
