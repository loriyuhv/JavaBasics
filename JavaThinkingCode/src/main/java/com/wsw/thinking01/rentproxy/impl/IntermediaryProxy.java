package com.wsw.thinking01.rentproxy.impl;

import com.wsw.thinking01.rentproxy.RentHouse;

/**
 * @author loriyuhv
 * @date 2025/9/5 9:52
 * @description 代理角色：中介（可以代理“房东”也可以代理“我”）
 */
public class IntermediaryProxy implements RentHouse {
    private final RentHouse target;   // 被代理的“真实对象” 可以是房东，也可以是我

    public IntermediaryProxy(RentHouse target) {
        this.target = target;
    }

    @Override
    public void rent() {
        before();
        target.rent();
        after();
    }

    private void before() {
        System.out.println("【中介】：带看3套房、砍价、准备合同...");
    }

    private void after() {
        System.out.println("【中介】：合同备案、售后保修一年！");
    }
}
