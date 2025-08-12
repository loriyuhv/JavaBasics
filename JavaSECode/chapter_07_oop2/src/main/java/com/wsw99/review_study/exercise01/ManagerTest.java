package com.wsw99.review_study.exercise01;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description TODO
 */
public class ManagerTest {
    public static void main(String[] args) {
        Manager jerry = new Manager("Jerry", "20197360", 3000.50, 1000);
        jerry.work();

        CommonEmployee commonEmployee = new CommonEmployee("Tom", "22013040216", 6000.40);
        commonEmployee.work();

    }
}
