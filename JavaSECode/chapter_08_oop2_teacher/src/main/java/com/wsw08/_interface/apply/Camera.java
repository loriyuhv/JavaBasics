package com.wsw08._interface.apply;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description TODO
 */
public class Camera implements USB {
    @Override
    public void start() {
        System.out.println("Camera start");
    }

    @Override
    public void stop() {
        System.out.println("Camera stop");
    }
}
