/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.service.EntitySocialService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceApi {

    @GET("service")
    Call<List<EntitySocialService>> getListSocialService();

    @GET("service/{id}")
    Call<EntitySocialService> getServiceById(@Path("id") String id);

    @POST("service")
    void addTask(@Body EntitySocialService service);
}
