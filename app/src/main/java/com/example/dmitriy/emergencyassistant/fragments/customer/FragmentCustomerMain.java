/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 2:04 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.customer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityCustomer;
import com.example.dmitriy.emergencyassistant.activities.based.ActivityCustomerSettings;
import com.example.dmitriy.emergencyassistant.activities.dialogs.lists.ActivityDialogSelectTask;
import com.example.dmitriy.emergencyassistant.elements.ElementStateSelect;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.interfaces.customer.OnSomeEventListener;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

/*
Фрмагмент основного интерфейса Needy с большими кнопками
 */

public class FragmentCustomerMain extends Fragment implements
        ActivityDialogSelectTask.OnSelectItem,
        InterfaceInitialize,
        InterfaceDataBaseWork {

    //Основные кнопки на главном экране "пациента"
    private Button
            btnCalls,
            btnSos,
            btnHome,
            btnShop,
            btnState,
            btnSettings;

    private LinearLayout lnButtons;
    private View v;


    //Объект локальной БД
    private DataBaseAppDatabase dataBase;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_customer_main, container, false);
        initializeScreenElements();
        //initializeDataBase();
        checkVisibleButtons();
        return v;
    }



    //Инициализируем элементы экрана
    @Override
    public void initializeScreenElements() {

        //Листенер кнопок
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //При нажатии на кнопку срабатывают методы в интерфейсе
                    //Которые потом срабатывают в активности
                    case R.id.btnCall:
                        ((ActivityCustomer)getActivity()).changeFrag();
                        break;
                    case R.id.btnSOS:
                        ((ActivityCustomer)getActivity()).sendSos();
                        break;
                    case R.id.btnHome:

                        showTasksWindow(0);
                        ((ActivityCustomer)getActivity()).sendHelpSignal(0);
                        break;
                    case R.id.btnShop:
                        showTasksWindow(1);
                        ((ActivityCustomer)getActivity()).sendHelpSignal(1);
                        break;
                        /*
                    case R.id.btn_CheckState:
                        someEventListener.checkState();
                        break;
                         */
                    case R.id.btn_Needy_Settings:
                        openSettings();
                        break;
                }
            }
        };


        //Инициализация кнопок
        btnCalls=v.findViewById(R.id.btnCall);
        btnCalls.setOnClickListener(oclBtn);

        btnSos=v.findViewById(R.id.btnSOS);
        btnSos.setOnClickListener(oclBtn);

        btnHome=v.findViewById(R.id.btnHome);
        btnHome.setOnClickListener(oclBtn);

        btnShop=v.findViewById(R.id.btnShop);
        btnShop.setOnClickListener(oclBtn);

        btnState =v.findViewById(R.id.btn_CheckState);
        btnState.setOnClickListener(oclBtn);

        btnSettings = v.findViewById(R.id.btn_Needy_Settings);
        btnSettings.setOnClickListener(oclBtn);

        lnButtons =v.findViewById(R.id.ln_Needy_HelpButtons);
    }



    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build(); }



    @Override
    public void initializeList() {}


    //Проверяем список подкл. соц рабоников и прячем/показываем кнопки
    private void checkVisibleButtons(){
        /*
        if(dataBase.dao_needy_volunteer().getAllUsers().isEmpty()){
            lnButtons.setVisibility(View.GONE);
        }
         */
    }


    //открытие активности настроек
    private void openSettings(){
        Intent i = new Intent(getContext(), ActivityCustomerSettings.class);
        startActivity(i);
    }

    //Открытие окна с выбором услуги
    private void showTasksWindow(int type){
        Intent i = new Intent(getContext(), ActivityDialogSelectTask.class);
        i.putExtra("select_type", type);
        startActivity(i);
    }



    //Выбор элемента
    @Override
    public void selectItem(ElementStateSelect element) {
        ((ActivityCustomer)getActivity()).sendHelpSignal(element.getType());
    }

}
