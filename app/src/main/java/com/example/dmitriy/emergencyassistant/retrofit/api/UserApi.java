/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 4:03 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @GET("user")
    Call<List<User>> getUsers();

    @GET("user/add/{user}")
    void addUser(@Path("user") String user);

    @GET("user/{id}")
    Call<User> getUserById(@Path("id") String id);


    @GET("user/{name}")
    public User getUserByName(@Path("name") User user);

    @POST("user")
    void addTask(@Body User user);
}