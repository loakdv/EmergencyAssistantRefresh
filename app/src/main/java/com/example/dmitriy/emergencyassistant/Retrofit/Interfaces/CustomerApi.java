package com.example.dmitriy.emergencyassistant.Retrofit.Interfaces;

import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Needy.POJOSignal;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Needy.POJOState;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Needy.POJOTask;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerApi {

    @POST("/api/task/sos")
    void sendSosSignal(@Body POJOSignal signal);

    @DELETE("/api/task/sos")
    void cancelSosSignal(@Path("id") String id);

    @POST("/api/task/")
    void sendNewTask(@Body POJOTask task);

    @POST("/api/task/home/")
    void sendHomeTask(@Body POJOTask task);

    @POST("/api/state/")
    void sendState(@Body POJOState state);

    @POST
    void sendVoiceMessage();
}
