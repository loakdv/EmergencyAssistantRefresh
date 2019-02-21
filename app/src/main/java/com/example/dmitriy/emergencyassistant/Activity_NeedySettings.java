package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class Activity_NeedySettings extends AppCompatActivity {

    //Временная переменная для загрузки данных
    SharedPreferences settingsPref;
    //Переменная для файла настроек
    public static final String APP_PREFERENCES = "settings";

    DataBase_AppDatabase dataBase;

    //Фрагменты
    Fragment_NeedySettings fMain;
    FragmentTransaction fTran;
    Fragment_NeedySettings_None fNone;

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
        fMain=new Fragment_NeedySettings();
        fNone=new Fragment_NeedySettings_None();
        if(dataBase.dao_profile().getProfile().isLogged()&&dataBase.dao_profile().getProfile().getType()==0){
            fTran=getSupportFragmentManager().beginTransaction();
            fTran.add(R.id.frameNeedySettings, fMain);
        }
        else {
            fTran=getSupportFragmentManager().beginTransaction();
            fTran.add(R.id.frameNeedySettings, fNone);
        }
        fTran.commit();
    }

    //Метод инициализации БД
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }

}
