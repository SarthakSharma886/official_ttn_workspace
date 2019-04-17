package com.example.introtojetpack.utils;

import android.graphics.Color;

import java.util.Random;

public abstract class ColorUtils {

    public static int getRandomColor(){
        Random random=new Random();
        int color= Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));

        return color;

    }

}
