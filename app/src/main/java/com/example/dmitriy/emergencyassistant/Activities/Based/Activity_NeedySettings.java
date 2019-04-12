package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.Fragments.Needy.Fragment_NeedySettings;
import com.example.dmitriy.emergencyassistant.Fragments.Needy.Fragment_NeedySettings_None;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.Services.Service_AlarmState;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_NeedySettings extends AppCompatActivity implements Fragment_NeedySettings.InterfaceNeedySettings{


    private DataBase_AppDatabase dataBase;


    //Фрагменты
    private Fragment_NeedySettings fragmentNeedySettings;
    private FragmentTransaction fragmentTransaction;
    private Fragment_NeedySettings_None fragmentNone;

    private FirebaseAuth mAuth;

    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeFirebase();

        initializeDataBase();

        setContentView(R.layout.activity_needysettings);

        setFragment();
    }




    //Метод для установки фрагмента в зависимости от загруженных данных
    private void setFragment(){

        fragmentNeedySettings = new Fragment_NeedySettings();
        fragmentNone = new Fragment_NeedySettings_None();

        if(user != null&&
                dataBase.dao_profile().getById(user.getUid()).getType() == 0){
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


    private void initializeFirebase(){
        FirebaseApp.initializeApp(getApplicationContext());
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }



    //Метод для запуска сервиса опроса состояния
    @Override
    public void startService() {
        startService(new Intent(this, Service_AlarmState.class));
    }


}
