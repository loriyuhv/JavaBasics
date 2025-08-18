package com.wsw02.selfdefine.exercise01;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description DAO测试类
 */
public class DAOTest {
    @Test
    public void test1() {
        DAO<User> dao = new DAO<>();
        dao.save("Alan", new User(1, 18, "Alan"));
        dao.save("Jerry", new User(2, 19, "Jerry"));
        dao.save("James", new User(3, 17, "James"));

        User jerry = dao.get("Jerry");
        System.out.println(jerry);

        dao.update("Jerry", new User(2, 24, "Jerry"));
        System.out.println(dao.get("Jerry"));
        dao.delete("James");
        System.out.println(dao.get("James"));

        dao.map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
