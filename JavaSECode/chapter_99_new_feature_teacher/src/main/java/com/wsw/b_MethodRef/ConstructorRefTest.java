package com.wsw.b_MethodRef;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>1. 构造器引用：和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。
 * 抽象方法的返回值类型即为构造器所属的类的类型</p>
 * @author loriyuhv
 * @version 1.0 2026/3/22 12:12
 * @since 1.0
 */
public class ConstructorRefTest {
    /* 构造器引用
    * Supplier中的T get()
    * Employee空参构造器 */
    @Test
    public void test01() {
        Supplier<Employee> supplier = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee(1L, "Jerry", 18, 23333.0);
            }
        };
        Employee employee = supplier.get();
        System.out.println(employee);

        Supplier<Employee> supplier1 = Employee :: new;
        Employee employee1 = supplier1.get();
        System.out.println(employee1);

    }

    /* Function中的R apply(T t) */
    @Test
    public void test2() {
        Function<Long, Employee> function1 = id -> new Employee(id);
        Employee apply = function1.apply(1L);
        System.out.println(apply);

        Function<Long, Employee> function2 = Employee :: new;
        Employee apply1 = function2.apply(2L);
        System.out.println(apply1);
    }

    /* 数组引用 */
    @Test
    public void test4() {
        Function<Integer, String[]> function1 = length -> new String[length];
        String[] apply = function1.apply(2);
        System.out.println(apply);

        Function<Integer, String[]> function2 = String[]::new;
        String[] apply1 = function2.apply(5);
        System.out.println(apply1);
    }
}
