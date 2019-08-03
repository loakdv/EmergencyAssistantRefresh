/*
 *
 *  Created by Dmitry Garmyshev on 8/3/19 12:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/22/19 3:38 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.adds;

import android.arch.persistence.room.Room;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
Диалоговое окно для создания заметок
*/

public class ActivityDialogAddNote extends AppCompatActivity implements
        InterfaceInitialize,
        InterfaceDataBaseWork {

    //Элементы которые используются при создании заметок
    private Button
            btnCancel,
            btnConfirm;

    //Поле для ввода заметки
    private EditText etAddNoteText;

    //База данных
    private DataBaseAppDatabase dataBase;

    //id выбранного Needy
    private String needyID;

    

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_addnote);
        initializeDataBase();
        getIntentExtras();
        initializeScreenElements();
    }


    //Инициализируем элементы на экране
    @Override
    public void initializeScreenElements() {

        //Листенер кнопок
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_CancelAddNote:
                        finish();
                        break;
                    case R.id.btn_SaveAddNote:

                        //Проверка поля ввода на пустоту
                        if(etAddNoteText.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),
                                    "Вы не можете создать пустую заметку!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Получаем время, вставляем запись
                            Date currentTime= Calendar.getInstance().getTime();

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm");

                            createNote(etAddNoteText.getText().toString(), sdf.format(currentTime));
                            finish();
                        }
                        break;
                }
            }
        };

        //Инициализация элементов
        btnCancel=findViewById(R.id.btn_CancelAddNote);
        btnCancel.setOnClickListener(oclBtn);

        btnConfirm=findViewById(R.id.btn_SaveAddNote);
        btnConfirm.setOnClickListener(oclBtn);

        etAddNoteText=findViewById(R.id.et_AddNoteText);
    }


    /*
   Этот метод достаёт из Intent id пользователя,
   для которого нужно создать заметку
    */
    private void getIntentExtras(){
        String extraNeedyID=getIntent().getStringExtra("needyId");
        needyID=extraNeedyID;
    }




    //Метод для инициализации БД
    @Override
    public void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getApplicationContext(), DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().
                build();
    }

    //На данный момент ненужный метод, он просто лежит в интерфейсе
    @Override
    public void initializeList() {}



    private void createNote(String text, String date){
        /*
        dataBase.dao_volunteer_addedNeedy_note().
                insert(new EntityVolunteerAddedNeedyNote(text, date, needyID));
         */
    }






}
