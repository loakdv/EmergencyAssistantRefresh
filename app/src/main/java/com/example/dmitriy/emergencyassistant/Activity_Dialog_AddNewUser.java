package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    Button btn_Final;
    Button btn_Cancel;
    EditText et_NeedyId;


    //Переменная необходимая для определения в какой список именно добавлять пользователей
    //Список у врача/родственника или список у нуждающегося
    boolean isDoctor;

    //База данных для добавления записей
    DataBase_AppDatabase dataBase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newrelative);

        //Инициализируем базу данных
        initializeDataBase();

        //Достаём переменную которая устанавливается при создании активности
        boolean extra_isDoctor=getIntent().getBooleanExtra("doctor", false);
        isDoctor=extra_isDoctor;


        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_FinalAddRelat:

                        if(isDoctor){ addUserDoctor(); }
                        else { addUserSimple(); }

                        break;
                    case R.id.btn_CancelAddRelat:
                        finish();
                        break;
                }
            }
        };

        //Инициализация элементов
        et_NeedyId=findViewById(R.id.et_IDRelatDoc);

        btn_Final=findViewById(R.id.btn_FinalAddRelat);
        btn_Final.setOnClickListener(oclBtn);
        btn_Cancel=findViewById(R.id.btn_CancelAddRelat);
        btn_Cancel.setOnClickListener(oclBtn);
    }


    //Метод который инициализирует базу данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }


    //Метод который добавляет пользователя в базу данных доктора
    private void addUserDoctor(){

        if(et_NeedyId.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Вы не можете оставить поле пустым!", Toast.LENGTH_SHORT).show();
        }
        else {
            //Проверяем на наличие этого пользователя уже в базе
            if(dataBase.dao_relative_addedNeedy().getById(Long.parseLong(et_NeedyId.getText().toString()))==null){
                //Получаем ID из поля ввода
                long id=Long.parseLong(et_NeedyId.getText().toString());
                //Получаем ID текущего пользователя
                long relativeID= dataBase.dao_relative().getRelative().getId();
                //Вставляем новую запись в БД
                dataBase.dao_relative_addedNeedy().insert(new Entity_Relative_AddedNeedy("name", "surname", "middlename",
                        "info", relativeID, id));
                //Завершаем активность
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Пользователь с таким ID уже существует!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //Метод который добавляет пользователя в базу данных обычного пользователя

    /*
    Те же действия что и в addUserDoctor
     */
    private void addUserSimple(){
        if(et_NeedyId.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Вы не можете оставить поле пустым!", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dataBase.dao_added_relatives().getById(Long.parseLong(et_NeedyId.getText().toString()))==null){
                long id=Long.parseLong(et_NeedyId.getText().toString());
                long needy_id= dataBase.dao_needy().getNeedy().getId();
                dataBase.dao_added_relatives().insert(new Entity_Added_Relatives("Имя", "Фамилия", "Отчество",
                        Math.random() < 0.5,needy_id, id));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Пользователь с таким ID уже существует!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
