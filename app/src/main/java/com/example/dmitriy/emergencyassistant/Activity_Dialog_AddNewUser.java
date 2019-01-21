package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Activity_Dialog_AddNewUser extends AppCompatActivity {

     /*
    Диалоговое окно для меню добавления подключенных
     */

     private String begin_id;

    /*
    Кнопки принятия номера и отмены действия
    Принажатии на кнопку final должны забиваться
    данные в базу данных
     */

    Button btn_Final;
    Button btn_Cancel;
    EditText et_NeedyId;
    boolean doctor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newrelative);
        boolean loadeddoctor=getIntent().getBooleanExtra("doctor", false);
        doctor=loadeddoctor;


        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_FinalAddRelat:
                        if(doctor){
                            Fragment_NeedyList.addNeedy(et_NeedyId.getText().toString());
                        }

                        finish();
                        break;
                    case R.id.btn_CancelAddRelat:
                        finish();
                        break;
                }
            }
        };

        et_NeedyId=findViewById(R.id.et_IDRelatDoc);
        //Инициализация элементов
        btn_Final=findViewById(R.id.btn_FinalAddRelat);
        btn_Final.setOnClickListener(oclBtn);
        btn_Cancel=findViewById(R.id.btn_CancelAddRelat);
        btn_Cancel.setOnClickListener(oclBtn);
    }
}
