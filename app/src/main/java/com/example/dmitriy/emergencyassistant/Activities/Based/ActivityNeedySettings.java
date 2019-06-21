package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.Fragments.Needy.FragmentNeedySettings;
import com.example.dmitriy.emergencyassistant.Fragments.Needy.FragmentNeedySettingsNone;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.Services.ServiceAlarmState;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
Активность для настроек Needy
 */

public class ActivityNeedySettings extends AppCompatActivity implements FragmentNeedySettings.InterfaceNeedySettings{


    private DataBaseAppDatabase dataBase;


    //Фрагменты
    private FragmentNeedySettings fragmentNeedySettings;
    private FragmentTransaction fragmentTransaction;
    private FragmentNeedySettingsNone fragmentNone;

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

        fragmentNeedySettings = new FragmentNeedySettings();
        fragmentNone = new FragmentNeedySettingsNone();

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
                DataBaseAppDatabase.class, "note_database").
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
        /*
        startService(new Intent(this, ServiceAlarmState.class));
         */
    }


}
