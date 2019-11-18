/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/30/19 3:27 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;

public class ActivityVolunteerSettings extends AppCompatActivity {
    //Переменные которые нужны для доступа к файлам настроек раздела авторизации
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;

    Button btnBack,btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_settings);
        initializeScreenElements();
    }


    private void initializeScreenElements(){
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnVolunteerBackApp:
                        Intent i = new Intent(getApplicationContext(), ActivityVolunteer.class);
                        startActivity(i);
                        break;
                    case R.id.btnVolunteerExit:
                        logOut();
                        break;
                }
            }
        };

        btnBack = findViewById(R.id.btnVolunteerBackApp);
        btnBack.setOnClickListener(oclBtn);

        btnExit = findViewById(R.id.btnVolunteerExit);
        btnExit.setOnClickListener(oclBtn);
    }


    private void logOut(){
        setMainUserNickname("null");
        Intent main=new Intent(this, ActivityMain.class);
        startActivity(main);
    }


    private void setMainUserNickname(String nickname){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putString("mainUserNickname", nickname);
        editor.apply();
    }
}
