/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/17/19 10:11 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.model.service.SocialService;
import com.example.dmitriy.emergencyassistant.model.service.SocialServiceCatalog;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceApi {


    public static final String PREFIX = "/emergency/api/v1";

    //Получаем списки
    @GET(PREFIX+"/service")
    Call<List<SocialService>> getListSocialService();

    @GET(PREFIX+"/service/list")
    Call<List<SocialService>> getListSocialServiceFromDao();

    @GET(PREFIX+"/service/{id}")
    Call<SocialService> getServiceById(@Path("id") SocialService socialService);

    @POST(PREFIX+"/service")
    Call<SocialService> addTaskDao(@Body SocialService service);



    @DELETE(PREFIX+"/task/{id}")
    Call<TaskSocialService> delete(@Path("id") TaskSocialService socialService);

    @GET(PREFIX+"/add")
    void add();


    //////////////////////////// CATALOG \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    @GET(PREFIX+"/catalog")
    Call<List<SocialServiceCatalog>> getCatalogs();

    @GET(PREFIX+"/catalog/{id}")
    Call<SocialServiceCatalog> getCatalogById(@Path("id") SocialServiceCatalog socialServiceCatalog);

    @GET(PREFIX+"/catalog/{cid}/service/{id}")
    Call<SocialService> getServiceFromCatalogById(
            @Path("cid") SocialServiceCatalog catalog,
            @Path("id") SocialService service);

}
