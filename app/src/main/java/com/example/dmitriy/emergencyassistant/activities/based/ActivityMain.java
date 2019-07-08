package com.example.dmitriy.emergencyassistant.activities.based;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

 /*
    Данный класс нужен для того, что бы определить какую активность запускать
    на основе загруженных данных
     */

public class ActivityMain extends AppCompatActivity {


    /*
    //Необходимо для проверки пользователя
    private FirebaseAuth mAuth;

    private FirebaseUser user;

     */

    //База данных
    private DataBaseAppDatabase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initializeUser();

        initializeDataBase();

        checkUser();
    }




    /*
   0 - Needy
   1 - Relative
   2 - Volunteer
    */
    private void startNextActivity(){

        /*
        if(dataBase.dao_user().getById(mAuth.getUid()) != null){
            switch (dataBase.dao_user().getById(mAuth.getUid()).getType()){
                case 0:
                    Intent needy = new Intent(this, ActivityCustomer.class);
                    startActivity(needy);
                    break;

                case 1:
                    Intent volunteer = new Intent(this, ActivityVolunteer.class);
                    startActivity(volunteer);
                    break;

                    default:
                        Intent login = new Intent(this, ActivityLogin.class);
                        startActivity(login);



            }
        }
        else {
            Intent login = new Intent(this, ActivityLogin.class);
            startActivity(login);
        }
        */
    }




    private void checkUser(){

        /*
        if(user != null){{
            startNextActivity(); }
        }
        else{
            Intent login = new Intent(this, ActivityLogin.class);
            startActivity(login);
        }
         */
    }




    //Метод для инициализации БД
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
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

        /*
        FirebaseApp.initializeApp(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
         */
    }



}
