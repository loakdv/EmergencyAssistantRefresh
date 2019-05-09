package com.example.dmitriy.emergencyassistant.Activities.Dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;

/*
Активность которая показывает оповещение о том что пользователю нужна помощь
 */

public class Activity_See_Task extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see__task);
        String init = getIntent().getStringExtra("Initials");
        int type = getIntent().getIntExtra("Type", 0);

        TextView text = findViewById(R.id.tv_TaskText);
        text.setText("Пользователь: \n"+init+"\n отправил сигнал SOS!");
    }
}
