package com.wsw.java;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m
 * OOM异常：java.lang.OutOfMemoryError
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 13:11
 * @since 1.0
 */
public class OOMTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Picture> pictures = new ArrayList<>();
        while (true) {
            Thread.sleep(20);
            pictures.add(new Picture(new Random().nextInt(1024*1024)));
        }
    }
}

class Picture {
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }

    public byte[] getPixels() {
        return pixels;
    }

    public void setPixels(byte[] pixels) {
        this.pixels = pixels;
    }
}
