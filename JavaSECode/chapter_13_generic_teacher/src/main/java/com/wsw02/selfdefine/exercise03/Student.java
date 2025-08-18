package com.wsw02.selfdefine.exercise03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 学生类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student<T> {
    private String name;
    private T score;
}
