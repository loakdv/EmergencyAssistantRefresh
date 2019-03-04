package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Activity_Dialog_AddNewUser extends AppCompatActivity {




     /*
    Диалоговое окно для меню добавления подключенных
     */




    /*
    Кнопки принятия номера и отмены действия
    Принажатии на кнопку final должны забиваться
    данные в базу данных
     */
    Button btnFinal;
    Button btnCancel;
    EditText etNeedyId;

    //Переменная необходимая для определения в
    // какой список именно добавлять пользователей
    //Список у врача/родственника или список у нуждающегося
    boolean isDoctor;

    int selectedType;
    //База данных для добавления записей
    DataBase_AppDatabase dataBase;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newrelative);



        //Инициализируем базу данных
        initializeDataBase();
        //Достаём переменную которая устанавливается при создании активности
        boolean extraIsDoctor=getIntent().getBooleanExtra("doctor", false);
        int extraType=getIntent().getIntExtra("type", 0);
        selectedType=extraType;
        isDoctor=extraIsDoctor;

        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_FinalAddRelat:
                        if(selectedType==1){
                            addUserDoctor();
                        }
                        else if(selectedType==2){
                            addUserSimple();
                        }


                        break;
                    case R.id.btn_CancelAddRelat:
                        finish();
                        break;
                }
            }
        };

        //Инициализация элементов
        etNeedyId=findViewById(R.id.et_IDRelatDoc);

        btnFinal=findViewById(R.id.btn_FinalAddRelat);
        btnFinal.setOnClickListener(oclBtn);
        btnCancel=findViewById(R.id.btn_CancelAddRelat);
        btnCancel.setOnClickListener(oclBtn);
    }


    //Метод который инициализирует базу данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    //Метод который добавляет пользователя в базу данных доктора
    private void addUserDoctor(){
        if(etNeedyId.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Вы не можете оставить поле пустым!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            //Проверяем на наличие этого пользователя уже в базе
            if(dataBase.dao_relative_addedNeedy().getById
                    (Long.parseLong(etNeedyId.getText().toString()))==null){
                //Получаем ID из поля ввода
                long id=Long.parseLong(etNeedyId.getText().toString());
                //Получаем ID текущего пользователя
                long relativeID= dataBase.dao_relative().getRelative().getId();
                //Вставляем новую запись в БД
                dataBase.dao_relative_addedNeedy().
                        insert(new Entity_Relative_AddedNeedy(
                                "name", "surname", "middlename",
                        "info", relativeID, id));
                //Завершаем активность
                Log.i("LOG_TAG", "--- New user added ---");
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),
                        "Пользователь с таким ID уже существует!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }




    //Метод который добавляет пользователя в базу данных обычного пользователя

    /*
    Те же действия что и в addUserDoctor
     */
    private void addUserSimple(){
        if(etNeedyId.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Вы не можете оставить поле пустым!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            if(dataBase.dao_added_relatives().getById(Long.parseLong(
                    etNeedyId.getText().toString()))==null){
                long id=Long.parseLong(etNeedyId.getText().toString());
                long needy_id= dataBase.dao_needy().getNeedy().getId();
                dataBase.dao_added_relatives().
                        insert(new Entity_Added_Relatives(
                                "Имя", "Фамилия", "Отчество",
                        Math.random() < 0.5,needy_id, id));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),
                        "Пользователь с таким ID уже существует!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }






}
