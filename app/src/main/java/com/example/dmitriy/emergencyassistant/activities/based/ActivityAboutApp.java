/*
 *
 *  Created by Dmitry Garmyshev on 8/3/19 12:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/22/19 3:38 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;

/*
Активность которая нужна для открытия раздела с описанием приложения
Всё что видит пользователь - информацию которая указана в XML файле
 */
public class ActivityAboutApp extends AppCompatActivity implements
        InterfaceInitialize {

    //Элементы экрана
    Button btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutapp);
        initializeScreenElements();
    }



   //Инициализируем элементы экрана с помощью метода из интерфейса
    @Override
    public void initializeScreenElements() {
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        btnExit=findViewById(R.id.btn_ExitAboutApp);
        btnExit.setOnClickListener(oclBtn);
    }



}
