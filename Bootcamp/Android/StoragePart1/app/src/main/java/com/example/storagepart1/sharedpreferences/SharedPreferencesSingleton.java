package com.example.storagepart1.sharedpreferences;


import android.app.Activity;
import android.content.Context;

import android.content.SharedPreferences;

public class SharedPreferencesSingleton {
    private static SharedPreferencesSingleton sharedPreferencesSingleton = null;

    private SharedPreferences mSharedPref;

    private SharedPreferencesSingleton(Context context) {
        mSharedPref = context.getSharedPreferences("SuperPrefs", Activity.MODE_PRIVATE);

    }

    public static SharedPreferencesSingleton init(Context context) {
        if (sharedPreferencesSingleton == null) {
            sharedPreferencesSingleton = new SharedPreferencesSingleton(context);
        }
        return sharedPreferencesSingleton;
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

    public void clear() {
        mSharedPref.edit().clear();
        mSharedPref.edit().apply();
    }
}