package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.service.EntityTaskSocialService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface TaskApi {

    @GET("task")
    Call<List<EntityTaskSocialService>> getTaskSocialServices();

    @GET("task/{id}")
    Call<EntityTaskSocialService> getServiceById();

    @POST("task")
    void addTask(@Body EntityTaskSocialService task);


}
