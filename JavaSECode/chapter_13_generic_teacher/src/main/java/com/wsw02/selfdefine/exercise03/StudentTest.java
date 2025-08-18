package com.wsw02.selfdefine.exercise03;

import org.junit.jupiter.api.Test;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 学生类测试
 */
public class StudentTest {
    // 语文老师
    @Test
    public void test1() {
        Student<String> student1 = new Student<>("Jerry", "优秀");
        System.out.println(student1);
    }

    // 数学老师老师
    @Test
    public void test2() {
        Student<Integer> student1 = new Student<>("Jerry", 89);
        System.out.println(student1);
    }
}
