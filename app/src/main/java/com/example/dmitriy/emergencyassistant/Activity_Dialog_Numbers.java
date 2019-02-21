package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Activity_Dialog_Numbers extends AppCompatActivity implements Adapter_Added_PhoneNumbers.CallBackButtons {

    /*
    Диалоговое окно для простотра списка подключённых
     */

    //Лист для хранения текущих номеров
    List<Entity_Added_PhoneNumbers> numbers=new ArrayList<Entity_Added_PhoneNumbers>();

    //Адаптер для списка номеров
    Adapter_Added_PhoneNumbers a_numbers;

    //RV на экране
    RecyclerView rv_Numbers;

    //Кнопки для отмены, добавления, и сохранения
    Button btn_Cancel;
    Button btn_Final;
    Button btn_Add;

    //Объект БД
    DataBase_AppDatabase dataBase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_numbers);

        //Инициализируем БД
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
                       Intent i=new Intent(getApplicationContext(), Activity_Dialog_AddNumber.class);
                       startActivity(i);
                       break;
               }
            }
        };

        //Нужные элементы
        btn_Cancel=findViewById(R.id.btn_CancelNumbers);
        btn_Cancel.setOnClickListener(oclBtn);
        btn_Final=findViewById(R.id.btn_FinalNumbers);
        btn_Final.setOnClickListener(oclBtn);
        btn_Add=findViewById(R.id.btn_AddNewNumber);
        btn_Add.setOnClickListener(oclBtn);

        //Инициализируем RV и список
        initializeList();
        initializeRecycleView();

    }


    //Метод инициализации базы данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
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
        rv_Numbers=findViewById(R.id.rv_Numbers);
        a_numbers=new Adapter_Added_PhoneNumbers(getBaseContext(), numbers,this);
        rv_Numbers.setAdapter(a_numbers);
        rv_Numbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
