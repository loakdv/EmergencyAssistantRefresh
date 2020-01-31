/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 9:38 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/18/19 8:56 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.info;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.dmitriy.emergencyassistant.adapters.login.AdapterLoginOrganizations;
import com.example.dmitriy.emergencyassistant.elements.ElementOrganization;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.model.organization.Organization;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//        organizations.add(new ElementOrganization("Департамент труда и социального развития",
//                "8 (423) 227-33-29",
//                "http://soctrud.primorsky.ru/"));
//
//        organizations.add(new ElementOrganization("Приморский центр социального обслуживания населения",
//                " 8 (423) 2604-314",
//                "http://pcson.ru/"));
//
//        organizations.add(new ElementOrganization("Седанкинский дом-интернат",
//                "233-43-07, 233-36-04",
//                "http://cdipi.vl.socinfo.ru"));



/*
Окно в котором отображается список доступных организаций в городе
 */
public class ActivityDialogOrganizationsList extends AppCompatActivity implements
        InterfaceInitialize {

    /*
    Лист с организациями
    Пока-что добавляются вручную из методом
     */
    private List<Organization> organizations = new ArrayList<Organization>();
    private AdapterLoginOrganizations adapterOrganizations;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations_list);
        initializeScreenElements();
        initializeList();


    }


    private void initializeRecycleView(){
        recyclerView=findViewById(R.id.rv_Organizations);
        adapterOrganizations=new AdapterLoginOrganizations(getBaseContext(), organizations);
        recyclerView.setAdapter(adapterOrganizations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void initializeScreenElements() {
        progressBar = findViewById(R.id.pbOLL);
        btnExit = findViewById(R.id.btnExitOrganizations);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initializeList(){

        
        LoadingAsync loadingAsync = new LoadingAsync();
        loadingAsync.execute();


    }


    private class LoadingAsync extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            NetworkService
                    .getInstance()
                    .getOrganizationApi()
                    .getOrganizationList().enqueue(new Callback<List<Organization>>() {
                @Override
                public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {

                    if (response.body() != null){
                        List<Organization> lOrganizations = response.body();
                        for(int i = 0; i < lOrganizations.size(); i ++){
                            System.out.println(lOrganizations.get(i).getPhone());
                            organizations.add(lOrganizations.get(i));
                        }

                        initializeRecycleView();
                    }

                }

                @Override
                public void onFailure(Call<List<Organization>> call, Throwable t) {

                }
            });
        return null;
        }
    }
}
