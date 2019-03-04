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
    Fragment_Login_FirstSelect fragmentFirstSelect;
    Fragment_LoginEnter fragmentEnter;
    Fragment_Login_CreateAccount fragmentCreate;
    Fragment_Login_Needy fragmentNeedy;
    Fragment_Login_Relative fragmentRelative;
    Fragment_Login_Volunteer fragmentVolunteer;

    DataBase_AppDatabase dataBase;

    //Транзакция
    FragmentTransaction fragmentTransaction;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeDataBase();

        //Инициализация фрагментов
        fragmentFirstSelect=new Fragment_Login_FirstSelect();
        fragmentEnter=new Fragment_LoginEnter();
        fragmentCreate=new Fragment_Login_CreateAccount();
        fragmentNeedy=new Fragment_Login_Needy();
        fragmentRelative=new Fragment_Login_Relative();
        fragmentVolunteer=new Fragment_Login_Volunteer();

        //Устанавливаем первый фрагмент
        setFirst();
    }




    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    //Имплементированные методы смены рабочего фрагмента
    @Override
    public void setFirst() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentFirstSelect);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void setCreate() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentCreate);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void setEnter() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentEnter);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void startMainAct() {
        settingsPref=this.getApplication().getSharedPreferences(
                APP_PREFERENCES, Context.MODE_PRIVATE);
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
        dataBase.dao_profile().insert(new Entity_Profile(type, surname,
                name, middlename, number, password, true));

        long profile_id=dataBase.dao_profile().getProfile().getId();

        if(type==0){
            dataBase.dao_needy().insert(new Entity_Needy(profile_id,
                    1, 1, 1, info, 0));
        }

        if(type==1&&doctor){
            dataBase.dao_relative().insert(new Entity_Relative(
                    profile_id, true));
        }

        else if(type==1&&!doctor){
            dataBase.dao_relative().insert(new Entity_Relative(
                    profile_id, false));
        }

        if(type==2){
            dataBase.dao_volunteer().insert(new Entity_Volunteer(
                    "Organization", profile_id));
        }

        Intent main=new Intent(this, Activity_Main.class);
        startActivity(main);
    }




    @Override
    public void setNeedy() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentNeedy);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void setRelative() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentRelative);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void setVolun() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentVolunteer);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




}
