/*
 *
 *  Created by Dmitry Garmyshev on 8/3/19 12:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/29/19 10:22 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.model.service.SocialService;
import com.example.dmitriy.emergencyassistant.model.service.SocialServiceCatalog;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.service.EntitySocialService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceApi {

    //Получаем списки
    @GET("service")
    Call<List<SocialService>> getListSocialService();

    @GET("service/list")
    Call<List<SocialService>> getListSocialServiceFromDao();


    @GET("service/{id}")
    Call<SocialService> getServiceById(@Path("id") SocialService socialService);

    @POST("service")
    void addTask(@Body SocialService service);

    @PUT("service/{id}")
    void update(@Path("id") SocialService socialService,
                @Body SocialService service);

    @DELETE("service/{id}")
    Call<SocialService> delete(@Path("id") SocialService socialService);

    @GET("add")
    void add();


    //////////////////////////// CATALOG \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    @GET("catalog")
    Call<List<SocialServiceCatalog>> getCatalogs();

    @GET("catalog/{id}")
    Call<SocialServiceCatalog> getCatalogById(@Path("id") SocialServiceCatalog socialServiceCatalog);

    @GET("catalog/{cid}/service/{id}")
    Call<SocialService> getServiceFromCatalogById(
            @Path("cid") SocialServiceCatalog catalog,
            @Path("id") SocialService service);

}
