package com.wsw.c_stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>1. Stream关注的是对数据的运算，与CPU打交道；集合关注的是数据的存储，与内存打交道；</p>
 * <p>2.</p>
 * <li>1) Stream本身不会存储元素</li>
 * <li>2) Stream不会改变源对象。相反，他们会返回一个持有结果的新Stream</li>
 * <li>3) Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行</li>
 * <p>3.Stream执行流程</p>
 * <li>1) Stream实例化</li>
 * <li>2) 一系列中间操作（过滤、映射、...)</li>
 * <li>3) 终止操作</li>
 * <p>4. 说明</p>
 * <li>1) 一个中间操作链，对数据源的数据进行处理</li>
 * <li>2) 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用</li>
 *
 * 测试实例化
 * @author loriyuhv
 * @version 1.0 2026/3/22 12:40
 * @since 1.0
 */
public class TestStreamAPI01 {
    /**
     * 创建Stream方式一：通过集合
     */
    @Test
    public void test01() {
        List<Employee> employees = EmployeeData.getEmployees();

        // default Stream<E> stream(); // 返回一个顺序流
        Stream<Employee> stream = employees.stream();

        // default Stream<E> parallelStream(); // 返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }

    /**
     * 创建Stream方式二：通过数组
     */
    @Test
    public void test02() {
        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};
        // 调用Arrays类的static <T> Stream<T> stream(T[] array) // 返回一个流
        IntStream stream = Arrays.stream(array);

        Employee e1 = new Employee(1L, "Tom");
        Employee e2 = new Employee(2L, "Jerry");
        Employee e3 = new Employee(3L, "Jack");

        Employee[] employees = new Employee[]{e1, e2, e3};

        Stream<Employee> stream1 = Arrays.stream(employees);
    }

    /**
     * 创建Stream方式三：通过Stream的of()
     */
    @Test
    public void test03() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
    }

    /**
     * 了解
     * 创建Stream方式四：创建无限流
     */
    @Test
    public void test04() {
        // 迭代：public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        // 遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        // 生成：public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
