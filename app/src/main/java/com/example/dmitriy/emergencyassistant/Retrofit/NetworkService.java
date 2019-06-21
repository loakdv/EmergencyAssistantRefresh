package com.example.dmitriy.emergencyassistant.Retrofit;

import com.example.dmitriy.emergencyassistant.Retrofit.Interfaces.AdminApi;
import com.example.dmitriy.emergencyassistant.Retrofit.Interfaces.CustomerApi;
import com.example.dmitriy.emergencyassistant.Retrofit.Interfaces.SocialWorkerApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "URL";
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
