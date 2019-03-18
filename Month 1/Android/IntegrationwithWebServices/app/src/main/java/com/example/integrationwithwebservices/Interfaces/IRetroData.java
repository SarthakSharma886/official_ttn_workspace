package com.example.integrationwithwebservices.Interfaces;

import com.example.integrationwithwebservices.POJO.RetroModel;


import retrofit2.Call;
import retrofit2.http.GET;

public interface IRetroData {

    @GET("posts.json")
    Call<RetroModel> getUsers();

}
