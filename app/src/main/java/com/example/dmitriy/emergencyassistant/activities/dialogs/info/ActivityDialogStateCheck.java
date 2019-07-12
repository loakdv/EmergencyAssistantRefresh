/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.adapters.customer.AdapterCustomerStateSelect;
import com.example.dmitriy.emergencyassistant.elements.ElementStateSelect;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOState;
import com.example.dmitriy.emergencyassistant.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/*
Активность которая нужна для сбора информации о состоянии пользователя
 */

public class ActivityDialogStateCheck extends AppCompatActivity implements
        AdapterCustomerStateSelect.CallBackButtons,
        InterfaceInitialize {


    //В этом листе хранятся элементы для выбора
    private List<ElementStateSelect> listSelect= new ArrayList<ElementStateSelect>();
    private AdapterCustomerStateSelect adapterSelect;

    //Элементы экрана
    private RecyclerView recyclerViewElementsList;
    private Button btnExit;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_checkstate);

        initializeScreenElements();

        fillList();
        initializeRecycleView();
    }




    @Override
    public void initializeScreenElements() {
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_CloseState:
                        finish();
                        break;
                }
            }
        };

        btnExit = findViewById(R.id.btn_CloseState);
        btnExit.setOnClickListener(oclBtn);

    }




    @Override
    public void select(ElementStateSelect state) {
        Toast.makeText(getApplicationContext(), "Готово!", Toast.LENGTH_SHORT).show();

        int percent = 0;

        switch (state.getType()){
            case 5:
                percent = 20;
                break;
            case 4:
                percent = 40;
                break;
            case 3:
                percent = 60;
                break;
            case 2:
                percent = 80;
                break;
            case 1:
                percent = 100;
                break;
        }

        //Отправляем состояние на сервер
        //METHOD


        finish();
    }




    //Метод обновления RV, нужен так же для обновления списка на экране
    private void initializeRecycleView(){
        recyclerViewElementsList=findViewById(R.id.rv_StatesList);
        adapterSelect=new AdapterCustomerStateSelect(
                getApplicationContext(), listSelect,
                this);


        recyclerViewElementsList.setAdapter(adapterSelect);
        recyclerViewElementsList.setLayoutManager(
                new LinearLayoutManager(getApplicationContext())
        );
    }



    /*
    Просто заполняем лист нужными элементами
    В них содержится выводимый текст и их
    значение для обработки
     */
    private void fillList(){
        listSelect.add(new ElementStateSelect("Очень хорошо", 1));
        listSelect.add(new ElementStateSelect("Хорошо", 2));
        listSelect.add(new ElementStateSelect("Нормально", 3));
        listSelect.add(new ElementStateSelect("Плохо", 4));
        listSelect.add(new ElementStateSelect("Очень плохо", 5));
    }




}
