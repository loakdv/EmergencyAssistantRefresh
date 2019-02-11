package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Debug extends AppCompatActivity {

    /*
    Данная активность нужна чисто для дебага
    Тут можно изменять абсолютно все параметры
    Все действия сохраняются по нажатию кнопки
     */

    //Временная переменная для сохранения настроек
    SharedPreferences settingsPref;
    public static final String APP_PREFERENCES = "settings";

    //Кнопки на экране
    //Состояние
    Button btn_State0;
    Button btn_State1;
    Button btn_State2;
    //Help
    Button btn_Help0;
    Button btn_Help1;
    Button btn_Help2;
    //SOS
    Button btn_Sos0;
    Button btn_Sos1;
    Button btn_Sos2;

    //Types
    Button btn_TypeNeedy;
    Button btn_TypeRelat;
    Button btn_TypeDoc;
    Button btn_TypeVolun;

    //Other
    Button btn_LoggedYes;
    Button btn_LoggedNo;
    Button btn_SaveInitDebug;

    //TextViews
    EditText et_surnameDebug;
    EditText et_nameDebug;
    EditText et_middlenameDebug;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        //Листенеры
        View.OnClickListener oclStates = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsPref=getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor settingsEditor=settingsPref.edit();
                switch (v.getId()){
                    case R.id.btn_State0:

                        break;
                    case R.id.btn_State1:

                        break;
                    case R.id.btn_State2:

                        break;


                    case R.id.btn_Help0:

                        break;
                    case R.id.btn_Help1:

                        break;
                    case R.id.btn_Help2:

                        break;

                    case R.id.btn_Sos0:

                        break;
                    case R.id.btn_Sos1:

                        break;
                    case R.id.btn_Sos2:

                        break;
                }
                settingsEditor.apply();
            }
        };

        View.OnClickListener oclTypes=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsPref=getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor settingsEditor=settingsPref.edit();
                 switch (v.getId()){
                     case R.id.btn_TypeNeedy:

                         break;
                     case R.id.btn_TypeRelat:

                         break;
                     case R.id.btn_TypeVolun:

                         break;

                 }
                 settingsEditor.apply();
            }
        };

        View.OnClickListener oclOther=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsPref=getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor settingsEditor=settingsPref.edit();
                switch (v.getId()){
                    case R.id.btn_LoggedYes:
                        settingsEditor.putBoolean("logged", true);
                        break;
                    case R.id.btn_LoggedNo:

                        break;
                    case R.id.btn_SaveInitDebug:

                        break;
                }
                settingsEditor.apply();
            }
        };


        //Инициализация элементов и присваивание листенеров
        btn_State0=findViewById(R.id.btn_State0);
        btn_State0.setOnClickListener(oclStates);
        btn_State1=findViewById(R.id.btn_State1);
        btn_State1.setOnClickListener(oclStates);
        btn_State2=findViewById(R.id.btn_State2);
        btn_State2.setOnClickListener(oclStates);

        btn_Help0=findViewById(R.id.btn_Help0);
        btn_Help0.setOnClickListener(oclStates);
        btn_Help1=findViewById(R.id.btn_Help1);
        btn_Help1.setOnClickListener(oclStates);
        btn_Help2=findViewById(R.id.btn_Help2);
        btn_Help2.setOnClickListener(oclStates);

        btn_Sos0=findViewById(R.id.btn_Sos0);
        btn_Sos0.setOnClickListener(oclStates);
        btn_Sos1=findViewById(R.id.btn_Sos1);
        btn_Sos1.setOnClickListener(oclStates);
        btn_Sos2=findViewById(R.id.btn_Sos2);
        btn_Sos2.setOnClickListener(oclStates);

        btn_TypeNeedy=findViewById(R.id.btn_TypeNeedy);
        btn_TypeNeedy.setOnClickListener(oclTypes);
        btn_TypeRelat=findViewById(R.id.btn_TypeRelat);
        btn_TypeRelat.setOnClickListener(oclTypes);
        btn_TypeDoc=findViewById(R.id.btn_TypeRelat);
        btn_TypeDoc.setOnClickListener(oclTypes);
        btn_TypeVolun=findViewById(R.id.btn_TypeVolun);
        btn_TypeVolun.setOnClickListener(oclTypes);

        btn_LoggedYes=findViewById(R.id.btn_LoggedYes);
        btn_LoggedYes.setOnClickListener(oclOther);
        btn_LoggedNo=findViewById(R.id.btn_LoggedNo);
        btn_LoggedNo.setOnClickListener(oclOther);

        btn_SaveInitDebug=findViewById(R.id.btn_SaveInitDebug);
        btn_SaveInitDebug.setOnClickListener(oclOther);

        et_surnameDebug=findViewById(R.id.et_surnameDebug);
        et_nameDebug=findViewById(R.id.et_nameDebug);
        et_middlenameDebug=findViewById(R.id.et_middlenameDebug);

    }
}
