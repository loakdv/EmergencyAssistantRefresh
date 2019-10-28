/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/17/19 10:11 PM
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

    public static final String PREFIX = "/emergency/api/v1/";

    @GET(PREFIX+"task")
    Call<List<TaskSocialService>> getTaskSocialServices();

    @GET(PREFIX+"task/{id}")
    Call<TaskSocialService> getTaskById(@Path("id") String id);

    @POST(PREFIX+"task")
    Call<TaskSocialService> addTask(@Body TaskSocialService task);

    @POST(PREFIX+"task/new")
    Call<TaskSocialServiceIds> addTaskId(@Body TaskSocialServiceIds ids);



}
