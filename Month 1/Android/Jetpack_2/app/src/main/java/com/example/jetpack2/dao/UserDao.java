package com.example.jetpack2.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jetpack2.pojo.UserData;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface UserDao {

    @Insert
    void insert(UserData userData);

    @Update
    void update(UserData userData);

    @Delete
    void delete(UserData userData);

    @Query("DELETE FROM user_table")
    void deleteAllData();

    @Query("SELECT * FROM user_table ")
    LiveData<List<UserData>> fetchAll();

}
