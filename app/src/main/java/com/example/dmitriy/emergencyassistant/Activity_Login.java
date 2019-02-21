package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
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
    Fragment_Login_Needy fNeedy;
    Fragment_Login_Relative fRelative;
    Fragment_Login_Volunteer fVolunteer;


    DataBase_AppDatabase dataBase;

    //Транзакция
    FragmentTransaction fTran;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeDataBase();

        //Инициализация фрагментов
        fFirstSelect=new Fragment_Login_FirstSelect();
        fEnter=new Fragment_LoginEnter();
        fCreate=new Fragment_Login_CreateAccount();
        fNeedy=new Fragment_Login_Needy();
        fRelative=new Fragment_Login_Relative();
        fVolunteer=new Fragment_Login_Volunteer();

        //Устанавливаем первый фрагмент
        setFirst();
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
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
        settingsPref=this.getApplication().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor=settingsPref.edit();
        settingsEditor.putBoolean("logged", true);
        settingsEditor.apply();
        //Достаём данные из хелпера
        int type=Helper_CreateProfile.type;
        String name=Helper_CreateProfile.name;
        String surname=Helper_CreateProfile.surname;
        String middlename=Helper_CreateProfile.middlename;
        String info=Helper_CreateProfile.info;
        String password=Helper_CreateProfile.password;
        String number=Helper_CreateProfile.phonenumber;
        boolean doctor=Helper_CreateProfile.doctor;
        //Зоздаём запись в БД
        dataBase.dao_profile().insert(new Entity_Profile(type, surname, name, middlename, number, password, true));

        long profile_id=dataBase.dao_profile().getProfile().getId();
        if(type==0){
            dataBase.dao_needy().insert(new Entity_Needy(profile_id, 1, 1, 1, info, 0));
        }

        if(type==1&&doctor){
            dataBase.dao_relative().insert(new Entity_Relative(profile_id, true));
        }

        else if(type==1&&!doctor){
            dataBase.dao_relative().insert(new Entity_Relative(profile_id, false));
        }

        if(type==2){
            dataBase.dao_volunteer().insert(new Entity_Volunteer("Organization", profile_id));
        }

        Intent main=new Intent(this, Activity_Main.class);
        startActivity(main);
    }

    @Override
    public void setNeedy() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frameContLogin, fNeedy);
        fTran.addToBackStack(null);
        fTran.commit();
    }

    @Override
    public void setRelative() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frameContLogin, fRelative);
        fTran.addToBackStack(null);
        fTran.commit();
    }

    @Override
    public void setVolun() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frameContLogin, fVolunteer);
        fTran.addToBackStack(null);
        fTran.commit();
    }
}
