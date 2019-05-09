package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

 /*
    Данный класс нужен для того, что бы определить какую активность запускать
    на основе загруженных данных
     */

public class Activity_Main extends AppCompatActivity {


    //Необходимо для проверки пользователя
    private FirebaseAuth mAuth;

    //База данных
    private DataBase_AppDatabase dataBase;

    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeUser();

        initializeDataBase();

        checkUser();
    }




    /*
   0 - Needy
   1 - Relative
   2 - Volunteer
    */
    private void startNextActivity(){

        if(dataBase.dao_profile().getById(mAuth.getUid()) != null){
            switch (dataBase.dao_profile().getById(mAuth.getUid()).getType()){
                case 0:
                    Intent needy = new Intent(this, Activity_Needy.class);
                    startActivity(needy);
                    break;
                case 1:
                    Intent relative = new Intent(this, Activity_DoctorRelative.class);
                    startActivity(relative);
                    break;
                case 2:
                    Intent volunteer = new Intent(this, Activity_Volunteer.class);
                    startActivity(volunteer);
                    break;
            }
        }
        else {
            Intent login = new Intent(this, Activity_Login.class);
            startActivity(login);
        }
    }




    private void checkUser(){
        if(user != null){{
            startNextActivity(); }
        }
        else{
            Intent login = new Intent(this, Activity_Login.class);
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
        checkUser();
    }


    private void initializeUser(){
        FirebaseApp.initializeApp(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }



}
