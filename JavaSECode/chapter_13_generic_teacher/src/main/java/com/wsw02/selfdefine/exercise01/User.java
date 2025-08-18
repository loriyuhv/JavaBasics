package com.wsw02.selfdefine.exercise01;

import lombok.*;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 用户类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private int age;
    private String name;
}
