package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Activity_Login extends AppCompatActivity implements Fragment_Login_FirstSelect.changeLoginFragment {

    /*
    Активность для создания аккаунта
     */

    //Временная переменная для сохранения настроек
    SharedPreferences settingsPref;
    public static final String APP_PREFERENCES = "settings";

    //Фрагменты для создания аккаунта и для авторизации
    Fragment_Login_FirstSelect fFirstSelect;
    Fragment_LoginEnter fEnter;
    Fragment_Login_CreateAccount fCreate;


    //Транзакция
    FragmentTransaction fTran;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Инициализация фрагментов
        fFirstSelect=new Fragment_Login_FirstSelect();
        fEnter=new Fragment_LoginEnter();
        fCreate=new Fragment_Login_CreateAccount();

        setFirst();
    }




    //Имплементированные методы смены рабочего фрагмента
    @Override
    public void setFirst() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frameContLogin, fFirstSelect);
        fTran.addToBackStack(null);
        fTran.commit();
    }

    @Override
    public void setCreate() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frameContLogin, fCreate);
        fTran.addToBackStack(null);
        fTran.commit();
    }

    @Override
    public void setEnter() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frameContLogin, fEnter);
        fTran.addToBackStack(null);
        fTran.commit();
    }

    @Override
    public void startMainAct() {
        //Сохраняем данные которые были указаны в меню создания аккаунта
        settingsPref=getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor=settingsPref.edit();
        settingsEditor.putInt("type", Profile.getType());
        settingsEditor.putBoolean("logged", Profile.isLogged());
        settingsEditor.putString("surname", Profile.getSurname());
        settingsEditor.putString("name", Profile.getName());
        settingsEditor.putString("middlename", Profile.getMiddlename());
        settingsEditor.putBoolean("doctor", Profile.isDoctor());
        settingsEditor.apply();
        Log.i("LOG_TAG", "--- Saved preferences! ---");
        //Запускаем основную активность всего приложения
        Intent main=new Intent(this, Activity_Main.class);
        startActivity(main);
    }
}
