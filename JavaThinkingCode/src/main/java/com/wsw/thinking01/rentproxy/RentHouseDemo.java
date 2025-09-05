package com.wsw.thinking01.rentproxy;

import com.wsw.thinking01.rentproxy.impl.IntermediaryProxy;
import com.wsw.thinking01.rentproxy.impl.Landlord;
import com.wsw.thinking01.rentproxy.impl.Roomer;

/**
 * @author loriyuhv
 * @date 2025/9/5 9:57
 * @description
 */
public class RentHouseDemo {
    public static void main(String[] args) {
        /*场景1：中介代理房东：房东只想收钱，其他不管*/
        Landlord landlord = new Landlord("James");
        IntermediaryProxy proxyForOwner = new IntermediaryProxy(landlord);
        System.out.println("=== 中介帮房东进行出租 ===");
        proxyForOwner.rent();
        System.out.println();

        /* 场景2：中介代理“我”——我只想入住，其他不管 */
        RentHouse me = new Roomer();
        RentHouse proxyForMe = new IntermediaryProxy(me);
        System.out.println("=== 中介帮我找房 ===");
        proxyForMe.rent();
    }
}
