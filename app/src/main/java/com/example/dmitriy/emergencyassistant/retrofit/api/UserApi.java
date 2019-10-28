/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/18/19 8:49 PM
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


    public static final String PREFIX = "/emergency/api/v1/";

    @GET(PREFIX+"user")
    Call<List<User>> getUsers();

    @GET(PREFIX+"user/add/{user}")
    Call<String> addUser(@Path("user") String user);

    @GET(PREFIX+"user/{id}")
    Call<User> getUserById(@Path("id") String id);


    @GET(PREFIX+"user/{name}")
    Call<User> getUserByName(@Path("name") User user);

    @GET(PREFIX+"user/{name}")
    Call<User> getUserByStringName(@Path("name") String name);

    @POST(PREFIX+"user")
    void addTask(@Body User user);



}