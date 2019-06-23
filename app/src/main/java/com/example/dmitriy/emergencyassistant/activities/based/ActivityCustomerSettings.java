package com.example.dmitriy.emergencyassistant.activities.based;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerSettings;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerSettingsNone;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
Активность для настроек Needy
 */

public class ActivityCustomerSettings extends AppCompatActivity implements FragmentCustomerSettings.InterfaceNeedySettings{


    private DataBaseAppDatabase dataBase;


    //Фрагменты
    private FragmentCustomerSettings fragmentCustomerSettings;
    private FragmentTransaction fragmentTransaction;
    private FragmentCustomerSettingsNone fragmentNone;

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

        fragmentCustomerSettings = new FragmentCustomerSettings();
        fragmentNone = new FragmentCustomerSettingsNone();

        if(user != null&&
                dataBase.dao_profile().getById(user.getUid()).getType() == 0){
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frameNeedySettings, fragmentCustomerSettings);
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
