package com.wsw01.use.exer1;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author loriyuhv
 * @date 2025/8/17
 * @description Employee
 */
public class EmployeeTest {
    // 1）使用Employee实现Comparable接口，并按name排序
    @Test
    public void test1() {
        TreeSet<Employee> employees = new TreeSet<>();
        Employee jerry = new Employee("Jerry", 18, new MyDate(2001, 2, 1));
        Employee tom = new Employee("Tom", 19, new MyDate(2002, 4, 1));
        Employee alan = new Employee("Alan", 21, new MyDate(2001, 3, 1));
        Employee mary = new Employee("Mary", 11, new MyDate(2008, 7, 1));
        Employee james = new Employee("James", 16, new MyDate(2004, 7, 1));

        employees.add(jerry);
        employees.add(tom);
        employees.add(alan);
        employees.add(mary);
        employees.add(james);

        // 遍历
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void test2() {
        Comparator<Employee> comparator = new Comparator<>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                // 有问题 因为月或日的时候， 15日和1日比的都是1，5就和日比了
                // return o1.getBirthday().toString().compareTo(o2.getBirthday().toString());
                // 正确写法1
                // int yearDistance = o1.getBirthday().getYear() - o2.getBirthday().getYear();
                // if (yearDistance != 0) {
                //     return yearDistance;
                // }
                // int monthDistance = o1.getBirthday().getMonth() - o2.getBirthday().getMonth();
                // if (monthDistance != 0) {
                //     return monthDistance;
                // }
                // return o1.getBirthday().getDay() - o2.getBirthday().getDay();

                // 正确写法2
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
        };

        TreeSet<Employee> employees = new TreeSet<>(comparator);

        Employee e1 = new Employee("John", 18, new MyDate(2001, 12, 1));
        Employee e2 = new Employee("David", 19, new MyDate(2002, 4, 1));
        Employee e3 = new Employee("Jack", 21, new MyDate(2001, 3, 1));
        Employee e4 = new Employee("Lily", 11, new MyDate(2008, 7, 1));
        Employee e5 = new Employee("Daniel", 16, new MyDate(2004, 7, 1));
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);

        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee next = iterator.next();
            System.out.println(next);
        }
    }
}
