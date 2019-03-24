package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_Main extends AppCompatActivity {

    /*
    Данный класс нужен для того, что бы определить какую активность запускать
    на основе загруженных данных
     */

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    //База данных
    private DataBase_AppDatabase dataBase;




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
        if(dataBase.dao_profile().getById(mAuth.getUid())!=null){
            switch (dataBase.dao_profile().getById(mAuth.getUid()).getType()){
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
        else {
            Intent login=new Intent(this, Activity_Login.class);
            startActivity(login);
        }
    }




    private void checkUser(){
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){{
            startNextActivity();
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
        checkUser();
    }


    private void initializeUser(){
        FirebaseApp.initializeApp(getApplicationContext());
        mAuth=FirebaseAuth.getInstance();
    }

    private void seeLogs(){
        Entity_Relative relative = dataBase.dao_relative().getRelative();
        Entity_Profile profile= dataBase.dao_profile().getProfile();
        Log.d("SEE LOGS", "==========");
        Log.d("SEE LOGS", profile.getId());
        Log.d("SEE LOGS", profile.getEmail());
        Log.d("SEE LOGS", profile.getPassword());
        Log.d("SEE LOGS", profile.getSurname());
        Log.d("SEE LOGS", profile.getName());
        Log.d("SEE LOGS", profile.getMiddlename());

        /*
        Log.d("SEE LOGS", "==========");
        Log.d("SEE LOGS", relative.getProfile_id());
        Log.d("SEE LOGS", ""+relative.getId());
        Log.d("SEE LOGS", ""+relative.isDoctor());
        */



    }


}
