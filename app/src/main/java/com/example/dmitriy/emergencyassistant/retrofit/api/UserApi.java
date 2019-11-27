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


    public static final String PREFIX = "/emergency/api/v1/user";

    @GET(PREFIX)
    Call<List<User>> getUsers();

    @GET(PREFIX+"/{id}")
    Call<User> getUserById(@Path("id") User user);

    @GET(PREFIX+"/name/{name}")
    Call<User> getUserByName(@Path("name") String name);

    @GET(PREFIX+"{user}")
    Call<String> addUser(@Path("user") User user);

    @GET(PREFIX+"{id}")
    Call<String> updateUser(@Path("id") User user);

    @GET(PREFIX+"{id}")
    Call<String> deleteUser(@Path("id") User user);
}