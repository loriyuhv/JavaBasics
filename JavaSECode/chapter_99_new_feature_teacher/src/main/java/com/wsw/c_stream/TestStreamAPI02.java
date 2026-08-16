package com.wsw.c_stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 *
 * @author loriyuhv
 * @version 1.0 2026/8/15 14:07
 * @since 1.0
 */
public class TestStreamAPI02 {
    /**
     * 筛选与切片
     */
    @Test
    public void test01() {
        List<Employee> employees = EmployeeData.getEmployees();
        // filter(Predicate p) 接收Lambda，从流中排除某些元素
        Stream<Employee> stream = employees.stream();
        // 查询员工表中薪资大于5000的员工信息
        stream.filter(e -> e.getSalary() > 5000).forEach(System.out::println);

        // limit(n) 截断流，使其元素不超过给定数量
        stream = employees.stream();
        stream.limit(2).forEach(System.out::println);

        System.out.println();
        // skip(n) 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
        employees.stream().skip(1).forEach(System.out::println);

        System.out.println();
        // distinct() 筛选，通过流所生成元素的hashCode()和equals()去除重复元素
        employees.add(new Employee(1001L, "Jerry", 34, 6000.38));
        employees.stream().distinct().forEach(System.out::println);
    }

    /**
     * 映射
     */
    @Test
    public void test02() {
        // map(Function f) 接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，
        // 并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println();

        // 练习：获取员工姓名长度大于3的员工姓名
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream()
                .map(Employee::getName)
                .filter(e -> e.length() > 3)
                .forEach(System.out::println);
        System.out.println();

        // 练习2
        Stream<Stream<Character>> streamStream = list.stream().map(TestStreamAPI02::fromStringToStream);
        streamStream.forEach(s -> s.forEach(System.out::println));
        System.out.println();

        // flatMap(Function f) 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连成一个流
        Stream<Character> characterStream = list.stream().flatMap(TestStreamAPI02::fromStringToStream);
        characterStream.forEach(System.out::println);

    }

    /**
     * 将字符串中的多个字符构成的集合转换为对应的Stream的实例
     * @param str string字符串
     * @return Stream实例
     */
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }

    @Test
    public void test03() {
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

        // list1.add(list2);
        list1.addAll(list2);
        System.out.println(list1);
    }

    /**
     * 排序
     */
    @Test
    public void test04() {
        // sorted 产生一个新流，自然排序
        List<Integer> list = Arrays.asList(1, 2, 1, 5, 8, 10, 3, 4, 32, 22, 1, 3, 2, 6);
        list.stream().sorted().forEach(System.out::println);
        System.out.println();

        // 抛异常，原因：Employee类没有实现Comparable接口
        // List<Employee> employees = EmployeeData.getEmployees();
        // employees.stream().sorted().forEach(System.out::println);

        // sorted(Comparator com) 定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        // 比较年龄
        // employees.stream().sorted(
        //         Comparator.comparingInt(Employee::getAge)
        // ).forEach(System.out::println);
        // 年龄相等，比较薪资，薪资从高到低
        employees.stream().sorted(
                (e1, e2) -> {
                    int compare = Integer.compare(e1.getAge(), e2.getAge());
                    if (compare == 0) {
                        return -Double.compare(e1.getSalary(), e2.getSalary());
                    }
                    return compare;
                }
        ).forEach(System.out::println);
    }
}
