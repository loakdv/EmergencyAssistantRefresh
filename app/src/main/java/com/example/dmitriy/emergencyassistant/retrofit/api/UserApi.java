package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @GET("user")
    Call<List<User>> getUsers();

    @GET("user/{id}")
    Call<User> getUserById(@Path("id") String id);

    @POST("user")
    void addTask(@Body User user);
}