/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/17/19 10:11 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit;

import android.app.Service;

import com.example.dmitriy.emergencyassistant.retrofit.api.OrganizationApi;
import com.example.dmitriy.emergencyassistant.retrofit.api.ServiceApi;
import com.example.dmitriy.emergencyassistant.retrofit.api.SocialServiceTaskApi;
import com.example.dmitriy.emergencyassistant.retrofit.api.TaskApi;
import com.example.dmitriy.emergencyassistant.retrofit.api.UserApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "http://86.102.102.165:8999";
    private Retrofit mRetrofit;

    private NetworkService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static NetworkService getInstance(){
        if (mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }


    public OrganizationApi getOrganizationApi(){
        return mRetrofit.create(OrganizationApi.class);
    }
    public ServiceApi getServiceApi(){
        return  mRetrofit.create(ServiceApi.class);
    }

    public SocialServiceTaskApi getSocialServiceTaskApi(){
        return  mRetrofit.create(SocialServiceTaskApi.class);
    }

    public TaskApi getTaskApi(){
        return  mRetrofit.create(TaskApi.class);
    }

    public UserApi getUserApi(){
        return  mRetrofit.create(UserApi.class);
    }


}
