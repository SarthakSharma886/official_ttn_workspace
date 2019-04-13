package com.example.weeklyproject.interfaces;

import com.example.weeklyproject.POJO.FetchUserData;

import java.io.File;

public interface IActivityAdapterComm {

    void showFragment(FetchUserData dataModel);

    void shareImage(File image , String name);

}
