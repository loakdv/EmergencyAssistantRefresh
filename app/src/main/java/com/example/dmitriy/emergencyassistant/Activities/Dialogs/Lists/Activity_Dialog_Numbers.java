package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds.Activity_Dialog_AddNumber;
import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Added_PhoneNumbers;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Added_PhoneNumbers;

import java.util.ArrayList;
import java.util.List;

public class Activity_Dialog_Numbers extends AppCompatActivity implements Adapter_Added_PhoneNumbers.CallBackButtons {
    

    /*
    Диалоговое окно для простотра списка подключённых
     */

    //Лист для хранения текущих номеров
    private List<Entity_Added_PhoneNumbers> numbers=new ArrayList<Entity_Added_PhoneNumbers>();

    //Адаптер для списка номеров
    private Adapter_Added_PhoneNumbers adapterNumbers;

    //RV на экране
    private RecyclerView recyclerViewNumbers;

    //Кнопки для отмены, добавления, и сохранения
    private Button btnCancel;
    private Button btnFinal;
    private Button btnAdd;

    //Объект БД
    private DataBase_AppDatabase dataBase;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_numbers);

        initializeDataBase();

        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               switch (v.getId()){
                   case R.id.btn_CancelNumbers:
                       //Завершаем активность
                       finish();
                       break;
                   case R.id.btn_FinalNumbers:
                       //Завершаем активность
                       finish();
                       break;
                   case R.id.btn_AddNewNumber:

                       //Запускаем диалоговое окно для создания номера
                       Intent i=new Intent(getApplicationContext(),
                               Activity_Dialog_AddNumber.class);
                       startActivity(i);
                       break;
               }
            }
        };


        //Нужные элементы
        btnCancel=findViewById(R.id.btn_CancelNumbers);
        btnCancel.setOnClickListener(oclBtn);
        btnFinal=findViewById(R.id.btn_FinalNumbers);
        btnFinal.setOnClickListener(oclBtn);
        btnAdd=findViewById(R.id.btn_AddNewNumber);
        btnAdd.setOnClickListener(oclBtn);

        //Инициализируем RV и список
        initializeList();
        initializeRecycleView();

    }




    //Метод инициализации базы данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    //Метод инициализации листа
    private void initializeList(){
        //Достаём список записей из таблицы
        if(!(dataBase.dao_added_phoneNumbers().getAll()==null)){
            numbers=dataBase.dao_added_phoneNumbers().getAll();
        }
    }




    //Метод обновления RV, нужен так же для обновления списка на экране
    private void initializeRecycleView(){
        recyclerViewNumbers=findViewById(R.id.rv_Numbers);
        adapterNumbers=new Adapter_Added_PhoneNumbers(getBaseContext(), numbers,this);
        recyclerViewNumbers.setAdapter(adapterNumbers);
        recyclerViewNumbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }




    //Для обновления списка после закрытия диалогового окна
    @Override
    protected void onStart() {
        super.onStart();
        onResume();
    }


    @Override
    protected void onResume() {
        super.onResume();
        initializeList();
        initializeRecycleView();}




        //Методы из интерфейса, для свзяи с адаптером
    @Override
    public void deleteNumber(Entity_Added_PhoneNumbers number) {
        dataBase.dao_added_phoneNumbers().delete(number);
        initializeList();
        initializeRecycleView();
    }




    @Override
    public void updateNumber(Entity_Added_PhoneNumbers number) {

    }




}
