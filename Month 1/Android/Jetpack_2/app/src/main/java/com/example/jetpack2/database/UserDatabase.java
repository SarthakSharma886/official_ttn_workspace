package com.example.jetpack2.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.jetpack2.dao.UserDao;
import com.example.jetpack2.pojo.UserData;


@Database(entities = UserData.class, version = 1)

public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase ourInstance = null;

    public abstract UserDao userDao();


    public static synchronized UserDatabase getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return ourInstance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new OnCreateAsync(ourInstance).execute();
        }
    };


    private static class OnCreateAsync extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;

        public OnCreateAsync(UserDatabase userDatabase) {
            this.userDao = userDatabase.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            userDao.insert(new UserData("user 1"));
            userDao.insert(new UserData("user 2"));
            userDao.insert(new UserData("user 3"));
            userDao.insert(new UserData("user 4"));
            userDao.insert(new UserData("user 6"));
            userDao.insert(new UserData("user 7"));
            userDao.insert(new UserData("user 8"));
            userDao.insert(new UserData("user 9"));
            userDao.insert(new UserData("user 10"));
            userDao.insert(new UserData("user 11"));

            return null;
        }
    }

}
