package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class Activity_Dialog_SendedSignal extends AppCompatActivity {


    //Данное окно необходимо просто для высвечивания информации о том что сигнал отправлен

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_sendedsignal);
    }
}
