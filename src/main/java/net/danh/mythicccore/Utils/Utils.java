package net.danh.mythicccore.Utils;

import java.util.Random;

public class Utils {

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }
}
