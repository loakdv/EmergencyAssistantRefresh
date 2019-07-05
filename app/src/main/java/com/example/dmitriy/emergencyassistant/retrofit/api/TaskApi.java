package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.models.service.TaskSocialService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface TaskApi {

    @GET("task")
    Call<List<TaskSocialService>> getTaskSocialServices();

    @GET("task/{id}")
    Call<TaskSocialService> getServiceById();

    @POST("task")
    void addTask(@Body TaskSocialService task);


}
