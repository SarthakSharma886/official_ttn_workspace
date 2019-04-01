package com.example.jetpack2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jetpack2.data.manager.UserDataManager;
import com.example.jetpack2.pojo.UserData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserDataManager mUserDataManager;
    private LiveData<List<UserData>> mUserDataList;


    public UserViewModel(@NonNull Application application) {
        super(application);
        mUserDataManager = new UserDataManager(application);
        mUserDataList = mUserDataManager.fetchAll();

    }

    public void insert(UserData userData) {
        mUserDataManager.insert(userData);
    }

    public void update(UserData userData) {
        mUserDataManager.update(userData);
    }

    public void delete(UserData userData) {
        mUserDataManager.delete(userData);
    }

    public LiveData<List<UserData>> fetchAll() {
        return mUserDataList;
    }

    public void deleteAll() {
        mUserDataManager.deleteAll();
    }

}
