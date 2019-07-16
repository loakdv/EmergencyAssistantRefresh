/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 8:17 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.customer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
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

import com.example.dmitriy.emergencyassistant.adapters.customer.AdapterCustomerNumberForCall;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerAddedPhoneNumbers;

import java.util.ArrayList;
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

    //Объявляем интерфейс как поле, он необходим для связью с активностью
    private FragmentCustomerMain.onSomeEventListener someEventListener;

    //Элементы на экране
    private Button btnBack;
    private RecyclerView rv_Numbers;
    private View v;


    //Элементы нужные для списка
    private List<EntityCustomerAddedPhoneNumbers> numbers=new ArrayList<EntityCustomerAddedPhoneNumbers>();
    private AdapterCustomerNumberForCall a_calls;

    //База данных
    private DataBaseAppDatabase dataBase;


    /*
   Получаем контекст для интерфейса
   Без него ничего работать не будет
   Это нужно в каждом таком случае
    */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        someEventListener = (FragmentCustomerMain.onSomeEventListener) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_customer_calls, container, false);
        initializeScreenElements();
        //initializeDataBase();
        //initializeList();
        //initializeRecycleView();
        return v;
    }



    //Метод инициализации элементов на экране
    @Override
    public void initializeScreenElements() {

        rv_Numbers=v.findViewById(R.id.rv_Number_ForCall);

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnBack:
                        //Меняем основной фрагмент активности
                        someEventListener.changeFrag();
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
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }

    //Инициализация отображаемого на экране списка элементами из БД
    @Override
    public void initializeList(){
        /*
        if(!(dataBase.dao_added_phoneNumbers().getAll()==null)){
            numbers=dataBase.dao_added_phoneNumbers().getAll();
        }

         */
    }


    /*
    Данный метод инициализирует сам список с номерами
    Инициализация могла бы быть произведена в основном методе
    инициализации, но бывают моменты когда необходимо обновить список,
    а для этого нужно заного проделать действия по его инициализации
     */
    private void initializeRecycleView(){
        a_calls=new AdapterCustomerNumberForCall(getActivity() ,numbers, this);
        rv_Numbers.setAdapter(a_calls);
        rv_Numbers.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



    /*
    Метод создаёт интент с запросом на то что необходимо сделать звонок
    Создаётся интент -> даём ему номер для звонка -> открываем окно звонка
    с нужным нам номером
    В качесте параметра этот метод принимает объект номера телефона из
    БД, и из этого объекта уже достаётся сам номер
     */
    @Override
    public void call(EntityCustomerAddedPhoneNumbers number) {
        Intent call=new Intent(Intent.ACTION_DIAL);
        //call.setData(Uri.parse("tel:"+number.getNumber()));
        startActivity(call);
    }
}
