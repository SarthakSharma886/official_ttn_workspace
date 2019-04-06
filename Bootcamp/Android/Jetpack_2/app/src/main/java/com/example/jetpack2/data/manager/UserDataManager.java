package com.example.jetpack2.data.manager;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.example.jetpack2.dao.UserDao;
import com.example.jetpack2.database.UserDatabase;
import com.example.jetpack2.pojo.UserData;


import java.util.List;

public class UserDataManager {

    private UserDao mUserDao;
    private LiveData<List<UserData>> mUserDataList;

    public UserDataManager(Context context) {
        this.mUserDao = UserDatabase.getInstance(context).userDao();
        mUserDataList = mUserDao.fetchAll();
    }


    public void insert(UserData userData) {

        new InsertUserData(mUserDao).execute(userData);

    }


    public void update(UserData userData) {
        new UpdateUserData(mUserDao).execute(userData);
    }

    public void delete(UserData userData) {
        new DeleteUserData(mUserDao).execute(userData);
    }

    public LiveData<List<UserData>> fetchAll() {
        return mUserDataList;
    }

    public void deleteAll() {
        new DeleteAllUserData(mUserDao).execute();
    }


    private static class InsertUserData extends AsyncTask<UserData, Void, Void> {

        UserDao userDao;

        public InsertUserData(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserData... userData) {
            userDao.insert(userData[0]);
            return null;
        }
    }


    private static class UpdateUserData extends AsyncTask<UserData, Void, Void> {

        UserDao userDao;

        public UpdateUserData(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserData... userData) {
            userDao.update(userData[0]);
            return null;
        }
    }


    private static class DeleteUserData extends AsyncTask<UserData, Void, Void> {

        UserDao userDao;

        public DeleteUserData(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserData... userData) {
            userDao.delete(userData[0]);
            return null;
        }
    }


    private static class DeleteAllUserData extends AsyncTask<Void, Void, Void> {

        UserDao userDao;

        public DeleteAllUserData(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllData();
            return null;
        }
    }

}
