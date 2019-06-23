package com.example.dmitriy.emergencyassistant.retrofit.interfaces;

import com.example.dmitriy.emergencyassistant.retrofit.pojo.volunteer.POJOVolunteerNeedy;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOState;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SocialWorkerApi {

    @GET("customers/list/")
    Call<List<POJOVolunteerNeedy>> getCustomersList();

    @GET("customer/{id}/state")
    Call<List<POJOState>> getStateList(@Path("id") String id);

    @GET("customer/{id}/task/list")
    Call<List<POJOTask>> getTasksList(@Path("id") String id);

    @POST("customer/{id]/task/{taskId}/taken")
    void takeTask(@Path("id") String id, @Path("taskId") String taskId);

    @PUT("customer/{id}/task/{taskId}/")
    void makeNotes(@Path("id") String id, @Path("tskId") String taskId);

    @POST("customer/{id}/task/{taskId}/ready")
    void completeTask(@Path("id") String id, @Path("taskId") String taskId);
}
