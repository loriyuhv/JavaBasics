package com.wsw02._class.experiment01;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 学生 子类 继承 父类 Person
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {
    private long stuId;

    public void displayStuId() {
        System.out.println(stuId);
    }
}
