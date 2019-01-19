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
        Log.i("LOG_TAG", "--- Check login... ---");
        //Сначала проверяется, залогинен ли пользователь
        if(Profile.isLogged()){
            Log.i("LOG_TAG", "--- Login: "+Profile.isLogged()+" ---");
            Log.i("LOG_TAG", "--- Select activity... ---");
            //Затем выбирается нужная активность
            if(Profile.getType()==0){
                Intent Needy=new Intent(this, Activity_Needy.class);
                startActivity(Needy);
            }
            else if(Profile.getType()==1){
                Intent RelatDoc = new Intent(this, Activity_DoctorRelative.class);
                startActivity(RelatDoc);
            }
        }
        else {
            Log.i("LOG_TAG", "--- Login: "+Profile.isLogged()+" ---");
            Intent login=new Intent(this, Activity_Login.class);
            startActivity(login);
        }

    }


    //Метод загрузки данных для активностей(Тестовый)
    private void loadSettings(){


        settingsPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int savedtype=settingsPref.getInt("type", 0);
        int savedsos=settingsPref.getInt("sos_signal", 0);
        int savedhelp=settingsPref.getInt("help_signal", 0);
        int savedstate=settingsPref.getInt("state_signal", 0);
        boolean savedlogged=settingsPref.getBoolean("logged", true);
        boolean saveddoctor=settingsPref.getBoolean("doctor", false);
        String savedinfo=settingsPref.getString("info", "");
        String savedname=settingsPref.getString("name", "");
        String savedsurname=settingsPref.getString("surname", "");
        String savedmiddlename=settingsPref.getString("middlename", "");


        //Устанавливаем нужным классам нужные данные
        Profile.setLogged(savedlogged);
        Profile.setType(savedtype);
        Profile.setName(savedname);
        Profile.setDoctor(saveddoctor);
        Profile.setSurname(savedsurname);
        Profile.setMiddlename(savedmiddlename);
        Needy.setInfo(savedinfo);
        Needy.setSignalSOS(savedsos);
        Needy.setSignalHelp(savedhelp);
        Needy.setSignalState(savedstate);

        seeLogs();

    }

    //Эти методы нужны для повторного подключения к активности после создания профиля/фхода в профиль
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LOG_TAG", "--- Resume activity Main ---");
        onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LOG_TAG", "--- Start activity Main ---");
        startAct();
    }

    private void seeLogs(){
        //Выводим нужную нам информацию в логи
        Log.i("LOG_TAG", "--- Type: "+Profile.getType());
        Log.i("LOG_TAG", "--- Surname: "+Profile.getSurname());
        Log.i("LOG_TAG", "--- Name: "+Profile.getName());
        Log.i("LOG_TAG", "--- MiddleName: "+Profile.getMiddlename());
        Log.i("LOG_TAG", "--- Info: "+Needy.getInfo());
        Log.i("LOG_TAG", "--- SOS signal: "+Needy.getSignalSOS());
        Log.i("LOG_TAG", "--- HELP signal: "+Needy.getSignalHelp());
        Log.i("LOG_TAG", "--- State signal: "+Needy.getSignalState());
        Log.i("LOG_TAG", "--- Doctor: "+Profile.isDoctor());
    }
}
