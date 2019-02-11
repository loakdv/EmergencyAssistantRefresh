package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Activity_Main extends AppCompatActivity {

    /*
    Данный класс нужен для того, что бы определить какую активность запускать
    на основе загруженных данных
     */

    //Временная переменная для загрузки данных
    SharedPreferences settingsPref;
    //Переменная для файла настроек
    public static final String APP_PREFERENCES = "settings";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Сначала загружаем данные
        loadSettings();
        //Запускаем нужную активность
        startAct();
    }



    /*
   0 - Needy
   1 - Relative
   2 - Doctor
   3 - Volunteer
    */
    private void startAct(){

        //Сначала проверяется, залогинен ли пользователь
        if(true){


        }
        else {
            Intent login=new Intent(this, Activity_Login.class);
            startActivity(login);
        }

    }


    //Метод загрузки данных для активностей(Тестовый)
    private void loadSettings(){
    }

    //Эти методы нужны для повторного подключения к активности после создания профиля/фхода в профиль
    @Override
    protected void onResume() {
        super.onResume();
        onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startAct();
    }


}
