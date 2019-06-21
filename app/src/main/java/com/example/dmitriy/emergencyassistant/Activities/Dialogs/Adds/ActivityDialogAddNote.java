package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds;

import android.arch.persistence.room.Room;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelativeAddedNeedyNote;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
    Диалоговое окно для создания заметок
     */

public class ActivityDialogAddNote extends AppCompatActivity {



    //Элементы которые используются при создании заметок
    private  Button btnCancel;
    private Button btnConfirm;
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
        btnConfirm=findViewById(R.id.btn_SaveAddNote);
        btnCancel.setOnClickListener(oclBtn);
        btnConfirm.setOnClickListener(oclBtn);
        etAddNoteText=findViewById(R.id.et_AddNoteText);
    }




    //Метод для инициализации БД
    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    //Вставляем запись в БД
    private void createNote(String text, String date){
        dataBase.dao_relative_addedNeedy_note().
                insert(new EntityRelativeAddedNeedyNote(text, date, needyID));
    }



    private void getIntentExtras(){
        //Получаем id выбранного Needy
        String extraNeedyID=getIntent().getStringExtra("needyId");
        needyID=extraNeedyID;
    }

}
