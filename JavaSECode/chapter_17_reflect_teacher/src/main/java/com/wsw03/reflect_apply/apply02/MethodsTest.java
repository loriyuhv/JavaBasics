package com.wsw03.reflect_apply.apply02;

import com.wsw03.reflect_apply.data.Person;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author loriyuhv
 * @date 2025/8/19
 * @description
 */
public class MethodsTest {
    @Test
    public void test1() {
        Class<Person> personClass = Person.class;
        // getMethods()：获取到运行类本身及其所有的父类中声明为public权限的方法
        // Method[] methods = personClass.getMethods();
        // for (Method method : methods) {
        //     System.out.println(method);
        // }

        // getDeclaredMethods()：获取当前运行时类中声明的所有方法
        Method[] personClassDeclaredMethods = personClass.getDeclaredMethods();
        for (Method personClassDeclaredMethod : personClassDeclaredMethods) {
            System.out.println(personClassDeclaredMethod);
        }
    }

    /**
     * 注解信息
     * 权限修饰符 返回值类型 方法名(形参类型1 参数1, 形参类型2 参数2, ...) throw 异常类型1, ... {}
     */
    @Test
    public void test2() {
        Class<Person> personClass = Person.class;
        Method[] personClassDeclaredMethods = personClass.getDeclaredMethods();
        for (Method personClassDeclaredMethod : personClassDeclaredMethods) {
            // 1. 获取方法声明的注解
            Annotation[] personClassDeclaredMethodAnnotations = personClassDeclaredMethod.getAnnotations();
            for (Annotation personClassDeclaredMethodAnnotation : personClassDeclaredMethodAnnotations) {
                System.out.print(personClassDeclaredMethodAnnotation + "\t");
            }

            // 2. 权限修饰符
            System.out.print(Modifier.toString(personClassDeclaredMethod.getModifiers()) + "\t");

            // 3. 返回值类型
            // System.out.print(personClassDeclaredMethod.getReturnType().getName() + "\t");
            System.out.print(personClassDeclaredMethod.getReturnType().getSimpleName() + "\t");

            // 4. 方法名
            System.out.print(personClassDeclaredMethod.getName());
            System.out.print("(");
            // 5. 形参列表
            Class<?>[] personClassDeclaredMethodParameterTypes = personClassDeclaredMethod.getParameterTypes();
            for (int i = 0; i < personClassDeclaredMethodParameterTypes.length; i++) {
                if (personClassDeclaredMethodParameterTypes.length - 1 == i) {
                    // System.out.print(personClassDeclaredMethodParameterTypes[i].getName() + " args_" + i);
                    System.out.print(personClassDeclaredMethodParameterTypes[i].getSimpleName() + " args_" + i);
                    break;
                }
                // System.out.print(personClassDeclaredMethodParameterTypes[i].getName() + " args_" + i + ", ");
                System.out.print(personClassDeclaredMethodParameterTypes[i].getSimpleName() + " args_" + i + ", ");
            }
            System.out.print(") ");

            // 6. 抛出的异常
            Class<?>[] personClassDeclaredMethodExceptionTypes = personClassDeclaredMethod.getExceptionTypes();
            if (personClassDeclaredMethodExceptionTypes.length > 0) {
                System.out.print(" throws ");
                for (int i = 0; i < personClassDeclaredMethodExceptionTypes.length; i++) {
                    if (personClassDeclaredMethodExceptionTypes.length - 1 == i) {
                        // System.out.print(personClassDeclaredMethodExceptionTypes[i].getName());
                        System.out.print(personClassDeclaredMethodExceptionTypes[i].getSimpleName());
                        break;
                    }
                    // System.out.print(personClassDeclaredMethodExceptionTypes[i].getName() + ", ");
                    System.out.print(personClassDeclaredMethodExceptionTypes[i].getSimpleName() + ", ");
                }
            }
            System.out.println();
        }
    }
}
