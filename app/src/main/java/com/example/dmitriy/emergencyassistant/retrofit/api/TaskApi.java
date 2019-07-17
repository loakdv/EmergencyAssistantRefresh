/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 3:59 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.model.service.SocialServiceTask;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.service.EntityTaskSocialService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface TaskApi {

    @GET("task")
    Call<List<SocialServiceTask>> getTaskSocialServices();

    @GET("task/{id}")
    Call<EntityTaskSocialService> getTaskById(@Path("id") String id);

    @POST("task")
    void addTask(@Body SocialServiceTask task);



}
