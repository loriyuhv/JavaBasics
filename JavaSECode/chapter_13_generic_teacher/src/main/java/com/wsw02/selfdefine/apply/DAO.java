package com.wsw02.selfdefine.apply;

import java.util.List;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description DAO：data(database) access object。
 * 内部封装了操作数据库相关表的增删改查操作。（CRUD）
 */
public class DAO<T> {
    // 增
    public void insert(T bean) {
        // 通过相应的Sql语句，将bean对象写入到数据表中
    }

    // 删除
    public T deleteById(int id) {
        //略
        return null;
    }

    // 改
    public void update(int id, T bean) {
        // 略
    }

    // 查询
    public T getById(int id) {
        // 略
        return null;
    }

    // 查询多条记录
    public List<T> getAll() {
        // 略
        return null;
    }

    // 定义泛型方法
    // 比如：查询表中的记录数。（E：Long类型）
    // 比如：查询表中最大的生日。（E：Date类型）
    public <E> E getValue(String sql) {
        return null;
    }
}
