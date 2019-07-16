/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 7:22 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dmitriy.emergencyassistant.adapters.login.AdapterLoginOrganizations;
import com.example.dmitriy.emergencyassistant.elements.ElementOrganization;
import com.example.dmitriy.emergencyassistant.R;

import java.util.ArrayList;
import java.util.List;

/*
Окно в котором отображается список доступных организаций в городе
 */
public class ActivityDialogOrganizationsList extends AppCompatActivity {

    /*
    Лист с организациями
    Пока-что добавляются вручную из методом
     */
    private List<ElementOrganization> organizations = new ArrayList<ElementOrganization>();
    private AdapterLoginOrganizations adapterOrganizations;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations_list);
        initializeList();
        initializeRecycleView();
    }

    //Метод обновления RV, нужен так же для обновления списка на экране
    private void initializeRecycleView(){
        recyclerView=findViewById(R.id.rv_Organizations);
        adapterOrganizations=new AdapterLoginOrganizations(getBaseContext(), organizations);
        recyclerView.setAdapter(adapterOrganizations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private void initializeList(){
        organizations.add(new ElementOrganization("Департамент труда и социального развития",
                "8 (423) 227-33-29",
                "http://soctrud.primorsky.ru/"));

        organizations.add(new ElementOrganization("Приморский центр социального обслуживания населения",
                " 8 (423) 2604-314",
                "http://pcson.ru/"));

        organizations.add(new ElementOrganization("Седанкинский дом-интернат",
                "233-43-07, 233-36-04",
                "http://cdipi.vl.socinfo.ru"));
    }
}
