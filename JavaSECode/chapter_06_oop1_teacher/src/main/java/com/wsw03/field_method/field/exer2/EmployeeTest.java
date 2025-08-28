package com.wsw03.field_method.field.exer2;

/**
 * @author loriyuhv
 * @date 2025/8/29 6:57
 * @description
 */
public class EmployeeTest {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "Jack";
        employee.age = 22;
        employee.salary = 20000D;
        employee.birthday = new MyDate();
        employee.birthday.year = 2001;
        employee.birthday.month = 4;
        employee.birthday.day = 20;

        //打印员工信息
        System.out.println(
            "id = " + employee.id + ",name = " + employee.name +
            ", age = " + employee.age + ", salary = " + employee.salary +
            ", birthday = [" + employee.birthday.year + "年" + employee.birthday.month +
            "月" + employee.birthday.day + "日]"
        );
    }
}
