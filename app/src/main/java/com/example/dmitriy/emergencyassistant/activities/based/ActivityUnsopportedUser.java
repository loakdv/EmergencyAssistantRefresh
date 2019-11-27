/*
 *
 *  Created by Dmitry Garmyshev on 27.11.19 19:43
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 27.11.19 19:43
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;

public class ActivityUnsopportedUser extends AppCompatActivity {

    //Переменные которые нужны для доступа к файлам настроек раздела авторизации
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;

    //Кнопка для перезапуска приложения
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsopported_user);
        initializeScreenElements();
    }

    private void initializeScreenElements(){
        btnBack = findViewById(R.id.btnUnsupportedBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relogin();
            }
        });
    }


    //Очищаем поле хранящее идентификатор активного пользователя
    private void setMainUserNickname(String nickname){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putString("mainUserNickname", nickname);
        editor.apply();
    }

    private void relogin(){
        setMainUserNickname("null"); //Очищаем активного пользователя
        Intent main=new Intent(this, ActivityMain.class);
        startActivity(main); //Перезапускаем приложение
    }
}
