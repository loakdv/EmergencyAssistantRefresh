/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 3:55 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.api;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SocialServiceTaskApi {

    @GET("api/v2/task")
    Call<List<Map<String, String>>> taskList();
}
