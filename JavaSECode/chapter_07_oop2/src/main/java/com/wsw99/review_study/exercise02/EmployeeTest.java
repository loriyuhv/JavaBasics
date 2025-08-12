package com.wsw99.review_study.exercise02;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description TODO
 */
public class EmployeeTest {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];
        employees[0] = new Developer("Jerry", 22);
        employees[1] = new Developer("Tom", 23);
        employees[2] = new Manager("Alan", 28);
        double count = 0;
        for (Employee employee : employees) {
            count += employee.calMoney(90);
        }
        System.out.println(count);
    }
}
