/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 3:42 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.customer.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.activities.based.ActivityAboutApp;
import com.example.dmitriy.emergencyassistant.activities.based.ActivityMain;

public class BlockNavigation extends Fragment {

    //Переменные которые нужны для доступа к файлам настроек раздела авторизации
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;

    private View v;

    private Button btnCommit
            ,btnExit
            ,btnAboutApp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_customer_settings_appnavigation, container, false);
        initializeScreenElements();
        return v;
    }


    private void initializeScreenElements(){

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_confirmSettings:
                        startMain();
                        break;
                    case R.id.btn_NeedyAboutApp:
                        startAbout();
                        break;
                    case R.id.btn_DeleteProfileNeedy:
                        logOut();
                        break;
                }
            }
        };

        btnCommit = v.findViewById(R.id.btn_confirmSettings);
        btnCommit.setOnClickListener(oclBtn);

        btnAboutApp = v.findViewById(R.id.btn_NeedyAboutApp);
        btnAboutApp.setOnClickListener(oclBtn);

        btnExit = v.findViewById(R.id.btn_DeleteProfileNeedy);
        btnExit.setOnClickListener(oclBtn);
    }



    //Открываем окно с информацией о приложении
    private void startAbout(){
        Intent i = new Intent(getContext(), ActivityAboutApp.class);
        startActivity(i);
    }


    /*
    Метод который возвращает нас в активность Main
    После этого, Main проверяет какой сейчас пользователь активирован
    и открывает нужный раздел
    Грубо говоря - приложение перезапускается

    Метод используется для возвращения из меню настроек
     */
    private void startMain(){
        Intent i = new Intent(getContext(), ActivityMain.class);
        startActivity(i);
    }


    private void logOut(){
        setMainUserNickname("null");
        Intent main=new Intent(getContext(), ActivityMain.class);
        startActivity(main);
    }




    private void setMainUserNickname(String nickname){
        loginPreferences = getActivity().getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putString("mainUserNickname", nickname);
        editor.apply();
    }



}
