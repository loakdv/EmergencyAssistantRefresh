package com.example.dmitriy.emergencyassistant.retrofit.interfaces;

import com.example.dmitriy.emergencyassistant.retrofit.pojo.login.POJONeedy;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.login.POJOProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AdminApi {

    @POST("admin/customer")
    void newCustomer();

    @POST("admin/user")
    void newUser();

    @GET("admin/customer/list")
    Call<List<POJONeedy>> getCustomersList();

    @GET("admin/user/list")
    Call<List<POJOProfile>> getUsersList();

    @GET("admin/customer/{id}/")
    POJONeedy getCustomerInfo(@Path("id") String id);

    @GET("admin/user/{id}/")
    POJOProfile getUserInfo(@Path("id") String id);

}
