package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Activity_Dialog_AddNote extends AppCompatActivity {

    /*
    Диалоговое окно для создания заметок
     */

    //Элементы которые используются при создании заметок
    Button btn_Cancel;
    Button btn_Confirm;
    EditText et_AddNoteText;

    //База данных
    DataBase_AppDatabase dataBase;

    //ID выбранного Needy
    long needyID;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_addnote);

        //Инициализируем БД
        initializeDataBase();

        //Получаем id выбранного Needy
        long extraNeedyID=getIntent().getLongExtra("needy_id", 0);
        needyID=extraNeedyID;

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
                        if(et_AddNoteText.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Вы не можете создать пустую заметку!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Получаем время, вставляем запись
                            Date currentTime= Calendar.getInstance().getTime();
                            createNote(et_AddNoteText.getText().toString(), currentTime.toString());
                            finish();
                        }
                        break;
                }
            }
        };

        //Инициализация элементов
        btn_Cancel=findViewById(R.id.btn_CancelAddNote);
        btn_Confirm=findViewById(R.id.btn_SaveAddNote);
        btn_Cancel.setOnClickListener(oclBtn);
        btn_Confirm.setOnClickListener(oclBtn);
        et_AddNoteText=findViewById(R.id.et_AddNoteText);
    }


    //Метод для инициализации БД
    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }

    //Вставляем запись в БД
    private void createNote(String text, String date){
        dataBase.dao_relative_addedNeedy_note().insert(new Entity_Relative_AddedNeedy_Note(text, date, needyID));
    }

}
