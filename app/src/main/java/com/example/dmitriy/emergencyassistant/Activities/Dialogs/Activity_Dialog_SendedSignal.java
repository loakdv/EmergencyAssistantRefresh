package com.example.dmitriy.emergencyassistant.Activities.Dialogs;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Task;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Added_Relatives;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Activity_Dialog_SendedSignal extends AppCompatActivity {


    //Данное окно необходимо просто для высвечивания информации о том что сигнал отправлен
    //ак же сама ктивность отправляет сигнал

    private List<Entity_Added_Relatives> users=new ArrayList<Entity_Added_Relatives>();

    private DataBase_AppDatabase dataBase;


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();

        initializeDataBase();
        initializeList();
        sendSignalToUsers();
        setContentView(R.layout.activity_dialog_sendedsignal);
    }




    //Инициализация базы данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    //Перебираем список пользователей кому можно отправлять сигнал о помощи
    private void sendSignalToUsers(){

        Entity_Profile profile=dataBase.dao_profile().getProfile();

        for(int i=0; i<users.size(); i++){
            Log.i("SIGNAL", "SEND SIGNAL: "+users.get(i).getId());
            databaseReference.child("Users").child(users.get(i).getId()).child("Tasks").push().setValue(
                    new Firebase_Task(profile.getSurname()+" "+profile.getName()+" "+profile.getMiddlename(), profile.getId(), 0));

        }
    }




    //Инициализация листа
    private void initializeList(){
        if(!(dataBase.dao_added_relatives().getAll()==null)){
            users=dataBase.dao_added_relatives().getByDoc(false);
        }
    }




}
