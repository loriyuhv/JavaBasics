package com.wsw.java;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 产生10万个长度不超过10的字符串，包含a-z，A-Z
 *
 * @author loriyuhv
 * @version 1.0 2025/10/5 16:10
 * @since 1.0
 */
public class GenerateString {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("words.txt");

        for (int i = 0; i < 100_000; i++) {
            int length = (int) (Math.random() * (10 - 1 + 1) + 1);
            fw.write(getString(length) + "\n");
        }

        fw.close();
    }

    public static String getString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            // 65 ~ 90, 97 ~ 122
            int num = (int) (Math.random() * (90 - 65 + 1) + 65) + (int) (Math.random() * 2) * 32;
            str += (char) num;
        }
        return str;
    }
}
