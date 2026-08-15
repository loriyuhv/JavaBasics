package com.wsw.b_MethodRef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author loriyuhv
 * @version 1.0 2026/3/22 11:20
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private int age;
    private double salary;

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && Double.compare(salary, employee.salary) == 0 && Objects.equals(id, employee.id) && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary);
    }
}
