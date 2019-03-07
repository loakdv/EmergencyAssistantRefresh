package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Activity_Main extends AppCompatActivity {

    /*
    Данный класс нужен для того, что бы определить какую активность запускать
    на основе загруженных данных
     */


    SharedPreferences settingsPref;
    //Переменная для файла настроек
    public static final String APP_PREFERENCES = "settings";

    //База данных
    DataBase_AppDatabase dataBase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Loading().execute();
    }




    /*
   0 - Needy
   1 - Relative
   3 - Volunteer
    */
    private void startNextActivity(){

        switch (dataBase.dao_profile().getProfile().getType()){
            case 0:
                Intent needy=new Intent(this, Activity_Needy.class);
                startActivity(needy);
                break;
            case 1:

                Intent relative=new Intent(this, Activity_DoctorRelative.class);
                startActivity(relative);
                break;
            case 2:

                Intent volunteer=new Intent(this, Activity_Volunteer.class);
                startActivity(volunteer);
                break;
        }

    }




    private void checkDataBase(){
        settingsPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean savedlogged=settingsPref.getBoolean("logged", false);
        boolean logged=savedlogged;
        if(logged){{
            if(dataBase.dao_profile().getProfile().isLogged()){
                startNextActivity();
            }
        }}

        else{
            Intent login=new Intent(this, Activity_Login.class);
            startActivity(login);
        }
    }




    //Метод для инициализации БД
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
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
        checkDataBase();
    }


    class Loading extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            initializeDataBase();
            checkDataBase();
            return null;
        }
    }


}
