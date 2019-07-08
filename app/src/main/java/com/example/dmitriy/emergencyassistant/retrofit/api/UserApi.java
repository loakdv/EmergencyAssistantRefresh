package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @GET("user")
    Call<List<EntityUser>> getUsers();

    @GET("user/{id}")
    Call<EntityUser> getUserById(@Path("id") String id);

    @POST("user")
    void addTask(@Body EntityUser user);
}