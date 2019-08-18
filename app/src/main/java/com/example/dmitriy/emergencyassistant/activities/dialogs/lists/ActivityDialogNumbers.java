/*
 *
 *  Created by Dmitry Garmyshev on 8/18/19 10:33 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/17/19 10:58 AM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.lists;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.activities.dialogs.adds.ActivityDialogAddNumber;
import com.example.dmitriy.emergencyassistant.adapters.customer.AdapterCustomerAddedPhoneNumbers;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerAddedPhoneNumbers;

import java.util.ArrayList;
import java.util.List;

/*
    Диалоговое окно для простотра списка подключённых номеров
     */


public class ActivityDialogNumbers extends AppCompatActivity implements
        AdapterCustomerAddedPhoneNumbers.CallBackButtons,
        InterfaceDataBaseWork,
        InterfaceInitialize {


    //Лист для хранения текущих номеров
    private List<EntityCustomerAddedPhoneNumbers> numbers;

    //Адаптер для списка номеров
    private AdapterCustomerAddedPhoneNumbers adapterNumbers;

    //RV на экране
    private RecyclerView recyclerViewNumbers;

    //Кнопки для отмены, добавления, и сохранения
    private Button
            btnCancel,
            btnFinal,
            btnAdd;


    //Объект БД
    private DataBaseAppDatabase dataBase;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_numbers);

        initializeDataBase();
        initializeScreenElements();

        //Инициализируем RV и список
        initializeList();
        initializeRecycleView();

    }


    @Override
    public void initializeScreenElements() {

        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_CancelNumbers:
                        //Завершаем активность
                        finish();
                        break;
                    case R.id.btn_FinalNumbers:
                        //Завершаем активность
                        finish();
                        break;
                    case R.id.btn_AddNewNumber:

                        //Запускаем диалоговое окно для создания номера
                        Intent i=new Intent(getApplicationContext(),
                                ActivityDialogAddNumber.class);
                        startActivity(i);
                        break;
                }
            }
        };


        //Нужные элементы
        btnCancel=findViewById(R.id.btn_CancelNumbers);
        btnCancel.setOnClickListener(oclBtn);

        btnFinal=findViewById(R.id.btn_FinalNumbers);
        btnFinal.setOnClickListener(oclBtn);

        btnAdd=findViewById(R.id.btn_AddNewNumber);
        btnAdd.setOnClickListener(oclBtn);

    }


    /*
    Метод инициализации базы данных
    */
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().build();
    }




    /*
    Метод инициализации листа
     */
    @Override
    public void initializeList(){
        numbers  = new ArrayList<EntityCustomerAddedPhoneNumbers>();

        /*
        //Достаём список записей из таблицы
        if(!(dataBase.dao_added_phoneNumbers().getAllUsers()==null)){
            numbers=dataBase.dao_added_phoneNumbers().getAllUsers();
        }
         */
    }




    //Метод обновления RV, нужен так же для обновления списка на экране
    private void initializeRecycleView(){
        recyclerViewNumbers=findViewById(R.id.rv_Numbers);
        adapterNumbers=new AdapterCustomerAddedPhoneNumbers(getBaseContext(), numbers,this);
        recyclerViewNumbers.setAdapter(adapterNumbers);
        recyclerViewNumbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }




    //Для обновления списка после закрытия диалогового окна
    @Override
    protected void onStart() {
        super.onStart();
        onResume();
    }


    @Override
    protected void onResume() {
        super.onResume();
        initializeList();
        initializeRecycleView();}




        //Методы из интерфейса, для свзяи с адаптером
    @Override
    public void deleteNumber(EntityCustomerAddedPhoneNumbers number) {
        /*
        dataBase.dao_added_phoneNumbers().delete(number);
        initializeList();
        initializeRecycleView();
         */
    }









}
