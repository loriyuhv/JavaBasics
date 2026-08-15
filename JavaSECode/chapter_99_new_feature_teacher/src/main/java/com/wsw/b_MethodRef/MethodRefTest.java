package com.wsw.b_MethodRef;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用使用
 * <p>1. 使用情境：当要传递Lambda体的操作，已经有实现方法了，可以使用方法引用！</p>
 * <p>2. 方法引用，本质就是Lambda表达式，而Lambda表达式作为函数式接口的实例。所以
 * 方法引用，也就是函数式接口的实例。</p>
 *
 * <p>3. 使用格式：类（或对象）:: 方法名</p>
 * <p>4. 具体分为如下的三种情况：
 *     <ul>
 *         <li>情况1: 对象 :: 非静态方法</li>
 *         <li>情况2: 类 :: 静态方法</li>
 *         <li>情况3: 类 :: 非静态方法</li>
 *     </ul>
 * </p>
 * <p>5. 方法引用的要求：要求接口中的抽象方法的形参列表和返回值类型与
 * 方法引用的方法的形参列表和返回值类型相同！针对情况1和情况2</p>
 *
 *
 * @author loriyuhv
 * @version 1.0 2026/3/22 11:26
 * @since 1.0
 */
public class MethodRefTest {
    /*
    * 情况1:对象 :: 实例方法
    * Consumer中的void accept(T t)
    * PrintStream中的void println(T t)
    *  */
    @Test
    public void test1() {
        Consumer<String> consumer1 = e -> System.out.println(e);
        consumer1.accept("Hello World");

        PrintStream out = System.out;
        Consumer<String> consumer2 = out :: println;
        consumer2.accept("Hello Java");
    }

    /*
    * Supplier中的T get()
    * Employee中的String getName() */
    @Test
    public void test2() {
        Employee jack = new Employee(1000L, "Jack", 34, 8000.42);
        Supplier<String> supplier1 = new Supplier<>() {
            @Override
            public String get() {
                return jack.getName();
            }
        };
        System.out.println(supplier1.get());

        Employee jerry = new Employee(1001L, "Jerry", 34, 6000.38);
        Supplier<String> supplier2 = () -> jerry.getName();
        String s = supplier2.get();
        System.out.println(s);

        Employee tom = new Employee(1002L, "Tom", 24, 4000.38);
        Supplier<String> supplier3 = tom :: getName;
        s = supplier3.get();
        System.out.println(s);
    }

    /* 情况二：类 :: 静态方法
    * Comparator 中的int compare(T t1, T t2)
    * Integer中的静态int compare(T t1, T t2)
    *  */
    @Test
    public void test3() {
        Comparator<Integer> comparator1 = (a, b) -> Integer.compare(a, b);
        int compare1 = comparator1.compare(1, 2);
        System.out.println(compare1);


        Comparator<Double> comparator2 = Double::compare;

        int compare = comparator2.compare(3.2, 3.3);
        System.out.println(compare);

    }

    /**
     * Function中的R apply(T t)
     * Math中的Long round(Double d)
     */
    @Test
    public void test4() {
        Function<Double, Long> function1 = (a) -> Math.round(a);
        Long apply = function1.apply(3.49);
        System.out.println(apply);


        Function<Double, Long> function2 = Math::round;
        Long apply2 = function2.apply(3.49);
        System.out.println(apply2);
    }

    /**
     * 情况3: 类 :: 实例方法
     * Comparator中的int compare(T t1, T t2)
     * String中的int t1.compareTo(t2)
     */
    @Test
    public void test5() {
        Comparator<String> comparator1 = (a, b) -> a.compareTo(b);
        int compare1 = comparator1.compare("a", "b");
        System.out.println(compare1);

        Comparator<String> comparator2 = String::compareTo;
        int compare2 = comparator2.compare("b", "a");
        System.out.println(compare2);
    }

    /**
     * BiPredicate中boolean test(T t1, T t2);
     * String 中 boolean t1.equals(t2);
     */
    @Test
    public void test6() {
        BiPredicate<String, String> pre1 = (a, b) -> a.equals(b);
        System.out.println(pre1.test("a", "b"));

        BiPredicate<String, String> pre2 =String::equals;
        boolean test = pre2.test("Jerry", "Jerry");
        System.out.println(test);
    }

    /**
     * Function中的R apply(T t);
     * Employee中的String getName();
     */
    @Test
    public void test7() {
        Function<Employee, String> fun = Employee::getName;
        Employee jack = new Employee(1000L, "Jack", 34, 8000.4);
        System.out.println(fun.apply(jack));
    }
}
