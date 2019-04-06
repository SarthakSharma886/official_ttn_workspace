package com.example.weeklyproject.singletons;


import android.app.Activity;
import android.content.Context;

public class SharedPreferences {
    private static SharedPreferences sharedPreferences = null;

    private android.content.SharedPreferences mSharedPref;

    private SharedPreferences(Context context) {
        mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);

    }

    public static SharedPreferences init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = new SharedPreferences(context);
        }
        return sharedPreferences;
    }


    public boolean contains(String key) {
        return mSharedPref.contains(key);
    }


    public String readString(String key) {
        return mSharedPref.getString(key, "");
    }

    public void write(String key, String value) {
        mSharedPref.edit().putString(key, value).apply();

    }

    public boolean readBoolean(String key) {
        return mSharedPref.getBoolean(key, false);
    }

    public void write(String key, boolean value) {
        mSharedPref.edit().putBoolean(key, value).apply();

    }

    public int readInt(String key) {
        return mSharedPref.getInt(key, -1);
    }

    public void write(String key, int value) {
        mSharedPref.edit().putInt(key, value).apply();
    }

    public void clear(String key) {

        mSharedPref.edit().remove(key).apply();

    }
}