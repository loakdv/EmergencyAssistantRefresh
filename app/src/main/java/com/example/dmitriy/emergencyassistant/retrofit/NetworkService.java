package com.example.dmitriy.emergencyassistant.retrofit;

import com.example.dmitriy.emergencyassistant.retrofit.interfaces.AdminApi;
import com.example.dmitriy.emergencyassistant.retrofit.interfaces.CustomerApi;
import com.example.dmitriy.emergencyassistant.retrofit.interfaces.SocialWorkerApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "http://86.102.102.165/emergency/api/v1/";
    private Retrofit mRetrofit;

    private NetworkService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static NetworkService getInstance(){
        if (mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }


    public AdminApi getAdminApi(){
        return mRetrofit.create(AdminApi.class);
    }


    public CustomerApi getCustomerApi(){
        return mRetrofit.create(CustomerApi.class);
    }

    public SocialWorkerApi getSocialWorkerApi(){
        return mRetrofit.create(SocialWorkerApi.class);
    }



}
