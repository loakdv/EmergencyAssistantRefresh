package com.example.dmitriy.emergencyassistant.activities.Dialogs.Adds;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.retrofit.pojo.login.POJONeedy;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.login.POJOProfile;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.login.POJORelative;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;


  /*
    Диалоговое окно для меню добавления подключенных
     */


public class ActivityDialogAddNewUser extends AppCompatActivity {




    /*
    Кнопки принятия номера и отмены действия
    Принажатии на кнопку final должны забиваться
    данные в базу данных
     */
    private Button btnFinal;
    private Button btnCancel;
    private EditText etNeedyId;

    //Переменная необходимая для определения в
    // какой список именно добавлять пользователей
    //Список у врача/родственника или список у нуждающегося
    private boolean isDoctor;

    //Переменная нужна для определения типа пользователя
    //От неё идёт выбор, в какую БД кидать запись
    private int selectedType;

    //База данных для добавления записей
    private DataBaseAppDatabase dataBase;


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    //Список для хранения полученных с сервера пользователей и юзеров
    private List<POJOProfile> userList;
    private List<POJONeedy> needyList;
    private List<POJORelative> relativeList;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newrelative);

        //initializeFirebase();

        getIntentExtras();

        //Инициализируем базу данных
        initializeDataBase();


        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_FinalAddRelat:


                    case R.id.btn_CancelAddRelat:
                        finish();
                        break;
                }
            }
        };

        //Инициализация элементов
        etNeedyId = findViewById(R.id.et_IDRelatDoc);

        btnFinal = findViewById(R.id.btn_FinalAddRelat);
        btnFinal.setOnClickListener(oclBtn);
        btnCancel = findViewById(R.id.btn_CancelAddRelat);
        btnCancel.setOnClickListener(oclBtn);
    }



    //Метод который инициализирует базу данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }



    private void getIntentExtras(){
        //Достаём переменную которая устанавливается при создании активности
        boolean extraIsDoctor=getIntent().getBooleanExtra("IS_DOCTOR", false);
        int extraType=getIntent().getIntExtra("TYPE", 0);

        selectedType=extraType;
        isDoctor=extraIsDoctor;
    }





    private void makeToast(String text){
        Toast.makeText(ActivityDialogAddNewUser.this, text, Toast.LENGTH_SHORT).show();
    }









}
