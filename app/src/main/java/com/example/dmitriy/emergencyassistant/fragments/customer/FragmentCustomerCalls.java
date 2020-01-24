/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/30/19 3:32 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.customer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityCustomer;
import com.example.dmitriy.emergencyassistant.adapters.customer.AdapterCustomerNumberForCall;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityUser;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityPhoneNumber;


import java.util.List;

/*
Фрагмент который отображает список для звонков у Needy
 */

/*
Реализуем интерфейс который используется в адаптере с номерами
Это нужно для того что-бы из адаптера иметь доступ к этому фрагменту
 */
public class FragmentCustomerCalls extends Fragment implements
        AdapterCustomerNumberForCall.CallBackButtons,
        InterfaceInitialize,
        InterfaceDataBaseWork {

    //Переменные которые нужны для доступа к файлам настроек раздела авторизации
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;


    //Элементы на экране
    private Button btnBack;
    private RecyclerView rvNumbers;
    private View v;


    //Элементы нужные для списка
    private List<EntityPhoneNumber> numbers;
    private AdapterCustomerNumberForCall a_calls;

    //База данных
    private DataBaseAppDatabase dataBase;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_customer_calls, container, false);
        initializeScreenElements();
        initializeDataBase();
        initializeList();
        initializeRecycleView();
        return v;
    }



    //Метод инициализации элементов на экране
    @Override
    public void initializeScreenElements() {

        rvNumbers=v.findViewById(R.id.rv_Number_ForCall);

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnBack:
                        //Меняем основной фрагмент активности
                        ((ActivityCustomer)getActivity()).showMainFragment();
                        break;
                }
            }
        };

        btnBack=v.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(oclBtn);
    }


    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();
    }



    //Инициализация отображаемого на экране списка элементами из БД
    public void initializeList(){

        if(!(dataBase.daoNumbers().getAllUsers()== null)){
            numbers = dataBase.daoNumbers().getByUserId(getCurrentUserId());
        }

    }


    /*
    Данный метод инициализирует сам список с номерами
    Инициализация могла бы быть произведена в основном методе
    инициализации, но бывают моменты когда необходимо обновить список,
    а для этого нужно заного проделать действия по его инициализации
     */
    private void initializeRecycleView(){
        a_calls=new AdapterCustomerNumberForCall(getActivity() ,numbers, this);
        rvNumbers.setAdapter(a_calls);
        rvNumbers.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



    /*
    Метод создаёт интент с запросом на то что необходимо сделать звонок
    Создаётся интент -> даём ему номер для звонка -> открываем окно звонка
    с нужным нам номером
    В качесте параметра этот метод принимает объект номера телефона из
    БД, и из этого объекта уже достаётся сам номер
     */
    @Override
    public void call(EntityPhoneNumber number) {
        Intent call=new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:"+number.getNumber()));
        startActivity(call);
    }


    private Long getCurrentUserId(){
        loginPreferences = getContext().getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String mainNickname = loginPreferences.getString("mainUserNickname", "null");

        if (!mainNickname.equals("null")){
            EntityUser entityUser = dataBase.daoUser().getByNickname(mainNickname);
            return entityUser.getId();
        }
        else return 0L;
    }
}
