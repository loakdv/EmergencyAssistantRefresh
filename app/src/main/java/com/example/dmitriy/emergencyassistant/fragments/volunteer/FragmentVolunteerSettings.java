/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:32 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 8:30 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.volunteer;

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

import com.example.dmitriy.emergencyassistant.activities.based.ActivityAboutApp;
import com.example.dmitriy.emergencyassistant.activities.based.ActivityMain;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.interfaces.volunteer.InterfaceVolunteerChangeFragments;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;

/*
Фрагмент который отображает окно настроек соц. работника
 */

public class FragmentVolunteerSettings extends Fragment implements
        InterfaceDataBaseWork,
        InterfaceInitialize {

    //Интерфейс для связи с основной активностью
    private InterfaceVolunteerChangeFragments changeFrag;

    //Элементы экрана
    private Button
            btnBack,
            btnDeleteProfile,
            btnAbout;

    //View используемая на текущем экране
    private View v;

    //Локальная БД
    private DataBaseAppDatabase dataBase;

    //Текущий пользователь записывается сюда, что-бы не обращаться к БД каждый раз
    private EntityUser profile;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeFrag=(InterfaceVolunteerChangeFragments) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_volunteer_settings, container, false);

        //initializeDataBase();
        initializeScreenElements();
        return v;
    }


    //Инициализируем элементы экрана
    @Override
    public void initializeScreenElements() {
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackVolunteer:
                        changeFrag.setMain();
                        break;
                    case R.id.btn_DeleteProfileVolun:
                        deleteProfile();
                        break;
                    case R.id.btn_VolunAboutApp:
                        startAbout();
                        break;
                }
            }
        };

        btnBack =v.findViewById(R.id.btn_BackVolunteer);
        btnBack.setOnClickListener(oclBtn);

        btnDeleteProfile =v.findViewById(R.id.btn_DeleteProfileVolun);
        btnDeleteProfile.setOnClickListener(oclBtn);

        btnAbout=v.findViewById(R.id.btn_VolunAboutApp);
        btnAbout.setOnClickListener(oclBtn);
    }


    @Override
    public void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        //profile=dataBase.dao_user().getProfile();
    }

    @Override
    public void initializeList() {}


    /*
    Удаляем профиль из БД
    (Отмечаем ключ сессии)
    Перезапускаем приложение
     */
    private void deleteProfile(){
        //dataBase.dao_user().delete(profile);
        Intent main=new Intent(getContext(), ActivityMain.class);
        startActivity(main);
    }

    //Открываем меню с информацией о приложении
    private void startAbout(){
        Intent i = new Intent(getContext(), ActivityAboutApp.class);
        startActivity(i);
    }
}
