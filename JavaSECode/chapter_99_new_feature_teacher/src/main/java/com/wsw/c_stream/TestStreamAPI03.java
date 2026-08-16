package com.wsw.c_stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试Stream的终止操作
 *
 * @author loriyuhv
 * @version 1.0 2026/8/16 12:53
 * @since 1.0
 */
public class TestStreamAPI03 {
    /**
     * 1 匹配与查找
     */
    @Test
    public void test01() {
        // allMatch(Predicate p) 检查是否匹配所有元素。
        // 练习：是否所有的员工的年龄都大于18岁
        List<Employee> employees = EmployeeData.getEmployees();
        boolean allMatch = employees.stream().allMatch(employee -> employee.getAge() > 18);
        System.out.println(allMatch);

        // anyMatch(Predicate p) 检查是否至少匹配一个元素。
        // 练习：是否存在员工的工资大于 7000
        boolean anyMatch = employees.stream().anyMatch(employee -> employee.getSalary() > 7000);
        System.out.println(anyMatch);

        // noneMatch(Predicate p) 检查是否没有匹配的元素。
        // 是否存在员工Jerry
        boolean noneMatch1 = employees.stream().noneMatch(employee -> employee.getName().equals("Marry"));
        System.out.println(noneMatch1);
        boolean noneMatch2 = employees.stream().noneMatch(employee -> employee.getName().contains("Jerry"));
        System.out.println(noneMatch2);

        // findFirst 返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);

        // findAny 返回当前流中的任意元素
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);
    }

    @Test
    public void test02() {
        // count 返回流中元素的总个数
        List<Employee> employees = EmployeeData.getEmployees();
        long count = employees.stream().filter(employee -> employee.getSalary() > 7000).count();
        System.out.println(count);

        // max(Comparator c) 返回流中最大值
        // 练习：返回最高工资
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
        Optional<Double> maxSalary = salaryStream.max(Double::compare);
        System.out.println(maxSalary);

        // min(Comparator c) 返回流中最小值
        // 练习：返回最低工资的员工
        Optional<Employee> minSalary = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(minSalary);

        // forEach(Consumer c) 内部迭代
        employees.stream().forEach(System.out::println);

        // 集合里的遍历方法 外部迭代
        employees.forEach(System.out::println);
    }

    /**
     * 2 归约
     */
    @Test
    public void test03() {
        // reduce(T identity, BinaryOperator) 可以将流中元素反复结合起来，得到一个值。返回T
        // 练习1: 计算1 - 10 的自然数的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // reduce(BinaryOperator) 可以将流中元素反复结合起来，得到一个值。返回Optional<T>
        // 练习2: 计算公司所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Optional<Double> salaryCount = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(salaryCount);
    }

    /**
     * 收集
     */
    @Test
    public void test04() {
        // collect(Collector c) 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
        // 练习1: 查找工资大于6000的员工，结果返回为一个List或Set
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> collect = employees.stream()
                .filter(employee -> employee.getSalary() > 6000)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        Set<Employee> set = employees.stream().filter(employee -> employee.getSalary() > 6000)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
    }
}
