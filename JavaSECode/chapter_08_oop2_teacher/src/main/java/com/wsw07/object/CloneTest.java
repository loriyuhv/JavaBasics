package com.wsw07.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * description
 *
 * @author loriyuhv
 * @version 2025/9/27 13:49
 * @since 1.0
 */
public class CloneTest {
    public static void main(String[] args) {
        Animal a1 = new Animal("阿黄");
        try {
            Animal a2 = (Animal) a1.clone();
            System.out.println("原始对象：" + a1);
            System.out.println("a1[name = " + a1.getName() + "]");
            a2.setName("阿绿");
            System.out.println("clone后的对象：" + a2);
            System.out.println("a2[name = " + a2.getName() + "]");
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Animal implements Cloneable {
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
