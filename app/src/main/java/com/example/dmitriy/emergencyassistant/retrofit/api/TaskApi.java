/*
 *
 *  Created by Dmitry Garmyshev on 8/3/19 12:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/29/19 1:14 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.model.service.TaskSocialService;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialServiceIds;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface TaskApi {

    @GET("task")
    Call<List<TaskSocialService>> getTaskSocialServices();

    @GET("task/{id}")
    Call<TaskSocialService> getTaskById(@Path("id") String id);

    @POST("task")
    Call<TaskSocialService> addTask(@Body TaskSocialService task);

    @POST("task/new")
    Call<TaskSocialServiceIds> addTaskId(@Body TaskSocialServiceIds ids);



}
