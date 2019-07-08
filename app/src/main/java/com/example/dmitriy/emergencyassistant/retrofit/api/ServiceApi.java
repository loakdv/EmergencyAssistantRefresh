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
