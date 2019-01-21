package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_Dialog_AddNewUser extends AppCompatActivity {

     /*
    Диалоговое окно для меню добавления подключенных
     */

    /*
    Кнопки принятия номера и отмены действия
    Принажатии на кнопку final должны забиваться
    данные в базу данных
     */
    Button btn_Final;
    Button btn_Cancel;
    EditText et_NeedyId;

    //Переменная необходимая для определения в какой список именно добавлять пользователей
    //Список у врача/родственника или список у нуждающегося
    boolean doctor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newrelative);
        //Достаём переменную которая устанавливается при создании активности
        boolean loadeddoctor=getIntent().getBooleanExtra("doctor", false);
        doctor=loadeddoctor;


        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_FinalAddRelat:
                        if(doctor){
                            //Добавляем нового пользователя в список врача/родственника
                            Fragment_SeeNeedyList.addNeedy(et_NeedyId.getText().toString());
                        }
                        //Завершаем активность после добавления
                        finish();
                        break;
                    case R.id.btn_CancelAddRelat:
                        //Завершаем активность
                        finish();
                        break;
                }
            }
        };
        //Инициализация элементов
        et_NeedyId=findViewById(R.id.et_IDRelatDoc);

        btn_Final=findViewById(R.id.btn_FinalAddRelat);
        btn_Final.setOnClickListener(oclBtn);
        btn_Cancel=findViewById(R.id.btn_CancelAddRelat);
        btn_Cancel.setOnClickListener(oclBtn);
    }
}
