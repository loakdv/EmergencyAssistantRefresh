/*
 *
 *  Created by Dmitry Garmyshev on 27.11.19 21:15
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 27.11.19 21:15
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.customer;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.activities.based.ActivityCustomer;
import com.example.dmitriy.emergencyassistant.adapters.customer.AdapterCustomerServices;
import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerNeedyList;
import com.example.dmitriy.emergencyassistant.model.service.SocialService;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.model.user.UserRole;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCustomerServiceSelect extends Fragment implements AdapterCustomerServices.OnSelectItem {

    private View mainView;

    private Button btnBack;
    private RecyclerView rvList;
    private List<SocialService> socialServices;
    private AdapterCustomerServices adapterCustomerServices;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_service_select, container, false);
        initializeScreenElements();
        initializeList();
        return mainView;
    }

    private void initializeScreenElements(){
        btnBack = mainView.findViewById(R.id.btnServicesBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityCustomer)getActivity()).showMainFragment();
            }
        });


    }

    private void initializeRecycleView(){
        rvList = mainView.findViewById(R.id.rvServicesList);
        Log.d("TAGTAG", "INITIALIZE RV");
        adapterCustomerServices=new AdapterCustomerServices(getContext(), socialServices, this);
        Log.d("TAGTAG", "SET ADAPTER");
        rvList.setAdapter(adapterCustomerServices);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    private void initializeList(){
        socialServices = new ArrayList<>();
        initializeRecycleView();
        LoadingAsync loadingAsync = new LoadingAsync();
        loadingAsync.execute();
    }


    //Async для загрузки тасков с сервера
    class LoadingAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            NetworkService.getInstance().
                    getServiceApi()
                    .getListSocialService().enqueue(new Callback<List<SocialService>>() {
                @Override
                public void onResponse(Call<List<SocialService>> call, Response<List<SocialService>> response) {
                    socialServices = response.body();
                    Log.d("TAGTAG", response.body().toString());
                    if(socialServices != null){
                        Log.d("TAGTAG", "NOT NULL");
                        initializeRecycleView();
                    }
                }

                @Override
                public void onFailure(Call<List<SocialService>> call, Throwable t) {

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }


    @Override
    public void onSelectItem(SocialService socialService) {
        ((ActivityCustomer)getActivity()).sendSos(socialService);
    }

}
