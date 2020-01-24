/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/17/19 10:11 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.model.service.SocialService;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialService;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialServiceIds;
import com.example.dmitriy.emergencyassistant.model.service.TaskStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface TaskApi {

    public static final String PREFIX = "/emergency/api/v1/task";

    @GET(PREFIX)
    Call<List<TaskSocialService>> getTaskSocialServices();

    @GET(PREFIX+"/{id}")
    Call<TaskSocialService> getTaskById(@Path("id") TaskSocialService taskSocialService);

    @POST(PREFIX)
    Call<TaskSocialService> addTask(@Body TaskSocialService task);

    @POST(PREFIX+"/new")
    Call<TaskSocialServiceIds> addTask(@Body TaskSocialServiceIds taskSocialServiceIds);

    @DELETE("/{id}")
    void delete(@Path("id") TaskSocialService taskSocialService);

    @PUT(PREFIX+"/{id}")
    Call<TaskSocialService> update(@Path("id") TaskSocialService id,
                                   @Body TaskSocialService service);

    @PUT(PREFIX+"/{id}")
    Call<TaskSocialService> update(@Path("id") Long id,
                                   @Body TaskSocialService service);

    @GET(PREFIX+"/{id}/update")
    Call<TaskSocialService> update(@Path("id") Long id,
                                   @Query("status")TaskStatus taskStatus,
                                   @Query("enable")boolean enable);
}
