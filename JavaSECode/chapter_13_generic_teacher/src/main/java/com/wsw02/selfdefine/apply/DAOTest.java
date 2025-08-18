package com.wsw02.selfdefine.apply;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description DAO测试
 */
public class DAOTest {
    @Test
    public void test() {
        CustomerDAO dao = new CustomerDAO();
        dao.insert(new Customer());
        List<Customer> all = dao.getAll();
    }

    @Test
    public void test2() {

    }
}
