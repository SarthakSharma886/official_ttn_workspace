package com.example.storagepart1.sharedpreferences;


import android.app.Activity;
import android.content.Context;

import android.content.SharedPreferences;

public class SharedPreferencesSingleton
{
    private static SharedPreferences mSharedPref;
    private static SharedPreferences.Editor mPrefsEditor ;

    private SharedPreferencesSingleton()
    {

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            mPrefsEditor = mSharedPref.edit();
    }


    public static boolean contains(String key){
        return mSharedPref.contains(key);
    }


    public static String readString(String key) {
        return mSharedPref.getString(key,"");
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor mPrefsEditor = mSharedPref.edit();
        mPrefsEditor.putString(key, value);
        mPrefsEditor.commit();
    }

    public static boolean readBoolean(String key) {
        return mSharedPref.getBoolean(key, false);
    }

    public static void write(String key, boolean value) {
        mPrefsEditor.putBoolean(key, value);
        mPrefsEditor.commit();
    }

    public static int readInt(String key) {
        return mSharedPref.getInt(key, -1);
    }

    public static void write(String key, int value) {
        mPrefsEditor.putInt(key, value).commit();
    }

    public static void clear(){
        mPrefsEditor.clear();
        mPrefsEditor.apply();
    }
}