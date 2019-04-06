package com.example.weeklyproject.interfaces;

import com.example.weeklyproject.POJO.AddUpdateUser;
import com.example.weeklyproject.POJO.LoginRegisterModel;
import com.example.weeklyproject.POJO.ResponseToken;
import com.example.weeklyproject.POJO.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("login")
    Call<ResponseToken> loginUser(@Body LoginRegisterModel loginModel);

    @POST("register")
    Call<ResponseToken> registerUser(@Body LoginRegisterModel registerModel);

    @GET("users")
    Call<UserList> getUserList(@Query("page") int page);

    @POST("users")
    Call<AddUpdateUser> addUser(@Body AddUpdateUser addUser);

    @POST("users/2")
    Call<AddUpdateUser> updateUser(@Body AddUpdateUser updateUser);

    @DELETE("users/2")
    Call<Void> deleteUser();


}
