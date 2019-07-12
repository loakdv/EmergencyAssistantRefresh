/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;

/*
Активность которая показывает оповещение о том что пользователю нужна помощь
 */

public class ActivityDialogSeeTask extends AppCompatActivity implements
        InterfaceInitialize {

    //Элементы экрана
    private Button btnClose;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_task);
        String init = getIntent().getStringExtra("Initials");
        int type = getIntent().getIntExtra("Type", 0);

        initializeScreenElements();
        text.setText("Пользователь: \n"+init+"\n отправил сигнал SOS!");
    }

    //Инициализируем элементы экрана
    @Override
    public void initializeScreenElements() {
        TextView text = findViewById(R.id.tv_TaskText);
        btnClose = findViewById(R.id.btn_close_task);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
