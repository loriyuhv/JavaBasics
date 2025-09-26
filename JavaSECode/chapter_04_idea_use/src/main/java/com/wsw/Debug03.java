package com.wsw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * description
 *
 * @author loriyuhv
 * @version 2025/9/26 13:11
 * @since 1.0
 */
public class Debug03 {
    public static void main(String[] args) {
        Person p1 = new Person(3);
        p1.setId(4);
        System.out.println(p1);
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private int id = 1;
    private String name;
    private int age;

    {
        id = 2;
    }

    public Person(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}