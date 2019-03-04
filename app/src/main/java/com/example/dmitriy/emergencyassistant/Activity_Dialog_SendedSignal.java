package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Activity_Dialog_SendedSignal extends AppCompatActivity {


    //Данное окно необходимо просто для высвечивания информации о том что сигнал отправлен
    //ак же сама ктивность отправляет сигнал

    List<Entity_Added_Relatives> users=new ArrayList<Entity_Added_Relatives>();

    DataBase_AppDatabase dataBase;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        for(int i=0; i<users.size(); i++){
            Log.i("SIGNAL", "SEND SIGNAL: "+users.get(i).getId());
        }
    }




    //Инициализация листа
    private void initializeList(){
        if(!(dataBase.dao_added_relatives().getAll()==null)){
            users=dataBase.dao_added_relatives().getByDoc(false);
        }
    }




}
