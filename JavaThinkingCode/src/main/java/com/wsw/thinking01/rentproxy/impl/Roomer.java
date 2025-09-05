package com.wsw.thinking01.rentproxy.impl;

import com.wsw.thinking01.rentproxy.RentHouse;

/**
 * @author loriyuhv
 * @date 2025/9/5 9:50
 * @description 真实解决：租客（我）
 */
public class Roomer implements RentHouse {
    @Override
    public void rent() {
        System.out.println("租客（我）终于拿到钥匙，拎包入住！");
    }
}
