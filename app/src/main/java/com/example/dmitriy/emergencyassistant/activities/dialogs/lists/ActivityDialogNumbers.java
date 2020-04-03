/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 6:53 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.lists;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityPhoneNumber;


import java.util.ArrayList;
import java.util.List;

/*
    Диалоговое окно для простотра списка подключённых номеров
     */


public class ActivityDialogNumbers extends AppCompatActivity implements
        AdapterCustomerAddedPhoneNumbers.CallBackButtons,
        InterfaceDataBaseWork,
        InterfaceInitialize {

    //Переменные которые нужны для доступа к файлам настроек раздела авторизации
    //Кусок вырван из активити логина т.к. нужны будут данные из настроек этого раздела
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;


    //Лист для хранения текущих номеров
    private List<EntityPhoneNumber> numbers;

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
    public void initializeList(){
        numbers  = new ArrayList<EntityPhoneNumber>();


        //Достаём список записей из таблицы
        if(!(dataBase.daoNumbers().getAllUsers()==null)){
            numbers = dataBase.daoNumbers().getByUserId(getCurrentUserId());
        }

    }




    //Метод обновления RV, нужен так же для обновления списка на экране
    private void initializeRecycleView(){
        recyclerViewNumbers=findViewById(R.id.rv_Numbers);
        adapterNumbers=new AdapterCustomerAddedPhoneNumbers(getBaseContext(), numbers,this);
        recyclerViewNumbers.setAdapter(adapterNumbers);
        recyclerViewNumbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }



    private Long getCurrentUserId(){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String mainNickname = loginPreferences.getString("mainUserNickname", "null");

        if (!mainNickname.equals("null")){
            EntityUser entityUser = dataBase.daoUser().getByNickname(mainNickname);
            return entityUser.getId();
        }
        else return 0L;
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
    public void deleteNumber(EntityPhoneNumber number) {
        dataBase.daoNumbers().delete(number);
        initializeList();
        initializeRecycleView();

    }









}
