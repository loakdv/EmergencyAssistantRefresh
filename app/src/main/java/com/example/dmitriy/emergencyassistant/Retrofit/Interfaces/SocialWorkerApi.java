package com.example.dmitriy.emergencyassistant.Retrofit.Interfaces;

import com.example.dmitriy.emergencyassistant.Retrofit.POJOVolunteerNeedy;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Needy.POJOState;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Needy.POJOTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SocialWorkerApi {

    @GET("/api/customers/list/")
    Call<List<POJOVolunteerNeedy>> getCustomersList();

    @GET("/api/customer/{id}/state")
    Call<List<POJOState>> getStateList(@Path("id") String id);

    @GET("/api/customer/{id}/task/list")
    Call<List<POJOTask>> getTasksList(@Path("id") String id);

    @POST("/api/customer/{id]/task/{taskId}/taken")
    void takeTask(@Path("id") String id, @Path("taskId") String taskId);

    @PUT("/api/customer/{id}/task/{taskId}/")
    void makeNotes(@Path("id") String id, @Path("tskId") String taskId);

    @POST("/api/customer/{id}/task/{taskId}/ready")
    void completeTask(@Path("id") String id, @Path("taskId") String taskId);
}
