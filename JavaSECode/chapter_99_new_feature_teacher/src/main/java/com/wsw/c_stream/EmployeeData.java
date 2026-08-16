package com.wsw.c_stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loriyuhv
 * @version 1.0 2026/3/22 11:24
 * @since 1.0
 */
public class EmployeeData {
    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1001L, "Jerry", 34, 6000.38));
        employees.add(new Employee(1002L, "Tom", 24, 4000.38));
        employees.add(new Employee(1003L, "Jack", 25, 8000.38));

        return employees;
    }
}
