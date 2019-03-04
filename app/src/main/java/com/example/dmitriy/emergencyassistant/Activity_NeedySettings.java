package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class Activity_NeedySettings extends AppCompatActivity {


    SharedPreferences settingsPref;
    public static final String APP_PREFERENCES = "settings";


    DataBase_AppDatabase dataBase;


    //Фрагменты
    Fragment_NeedySettings fragmentNeedySettings;
    FragmentTransaction fragmentTransaction;
    Fragment_NeedySettings_None fragmentNone;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDataBase();
        setContentView(R.layout.activity_needysettings);
        setFragment();
    }




    //Метод для установки фрагмента в зависимости от загруженных данных
    private void setFragment(){
        settingsPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        fragmentNeedySettings=new Fragment_NeedySettings();
        fragmentNone=new Fragment_NeedySettings_None();
        if(dataBase.dao_profile().getProfile().isLogged()&&
                dataBase.dao_profile().getProfile().getType()==0){
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frameNeedySettings, fragmentNeedySettings);
        }
        else {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frameNeedySettings, fragmentNone);
        }
        fragmentTransaction.commit();
    }




    //Метод инициализации БД
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




}
