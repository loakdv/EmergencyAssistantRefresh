package com.example.dmitriy.emergencyassistant.retrofit.interfaces;

import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOSignal;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOState;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOTask;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerApi {

    @POST("task/sos")
    void sendSosSignal(@Body POJOSignal signal);

    @DELETE("task/sos")
    void cancelSosSignal(@Path("id") String id);

    @POST("task/")
    void sendNewTask(@Body POJOTask task);

    @POST("task/home/")
    void sendHomeTask(@Body POJOTask task);

    @POST("state/")
    void sendState(@Body POJOState state);

    @POST
    void sendVoiceMessage();
}
