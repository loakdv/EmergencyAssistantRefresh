package com.example.dmitriy.emergencyassistant.Retrofit.Interfaces;

import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login.POJONeedy;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login.POJOProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AdminApi {

    @POST("/api/admin/customer")
    void newCustomer();

    @POST("/api/admin/user")
    void newUser();

    @GET("/api/admin/customer/list")
    Call<List<POJONeedy>> getCustomersList();

    @GET("/api/admin/user/list")
    Call<List<POJOProfile>> getUsersList();

    @GET("/api/admin/customer/{id}/")
    POJONeedy getCustomerInfo(@Path("id") String id);

    @GET("/api/admin/user/{id}/")
    POJOProfile getUserInfo(@Path("id") String id);

}
