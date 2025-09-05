package com.wsw.thinking01.rentproxy.impl;

import com.wsw.thinking01.rentproxy.RentHouse;

/**
 * @author loriyuhv
 * @date 2025/9/5 9:48
 * @description 真实角色：房东
 */
public class Landlord implements RentHouse {
    private final String name;

    public Landlord(String name) {
        this.name = name;
    }

    @Override
    public void rent() {
        System.out.println("房东：" + name + "收到租金，把钥匙交给租客！");
    }
}
