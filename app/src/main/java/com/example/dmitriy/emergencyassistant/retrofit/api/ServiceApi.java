package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.models.SocialService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceApi {

    @GET("service")
    Call<List<SocialService>> getListSocialService();

    @GET("service/{id}")
    Call<SocialService> getServiceById(@Path("id") String id);

    @POST("service")
    void addTask(@Body SocialService service);
}
