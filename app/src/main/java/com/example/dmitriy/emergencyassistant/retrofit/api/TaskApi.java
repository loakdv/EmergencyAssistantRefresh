/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

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
