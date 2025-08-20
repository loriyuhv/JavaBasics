package com.wsw04.other.annotation;

/**
 * @author loriyuhv
 * @date 2025/8/20
 * @description
 */
@Table("tb_customer")
public class Customer {
    @Column(columnName = "customer_name", columnType = "varchar(15)")
    private String name;
    @Column(columnName = "customer_age", columnType = "int")
    public int age;

    public Customer() {}

    public Customer(int age) {
        this.age = age;
    }

    private Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }


}
