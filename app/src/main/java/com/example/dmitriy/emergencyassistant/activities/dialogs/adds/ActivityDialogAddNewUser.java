/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 8:31 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.adds;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
Диалоговое окно для меню добавления юзер в БД
*/


public class ActivityDialogAddNewUser extends AppCompatActivity implements
        InterfaceInitialize,
        InterfaceDataBaseWork {

    /*
    Кнопки принятия номера и отмены действия
    Принажатии на кнопку final должны забиваться
    данные в базу данных
     */
    private Button
            btnCommit,
            btnCancel;

    private EditText etCustomerId;
    private LinearLayout lnLoading;

    //Переменная необходимая для определения в
    // какой список именно добавлять пользователей
    //Список у врача/родственника или список у нуждающегося
    private boolean isDoctor;

    //Переменная нужна для определения типа пользователя
    //От неё идёт выбор, в какую БД кидать запись
    private int selectedType;

    //База данных для добавления записей
    private DataBaseAppDatabase dataBase;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_add_new_user);
        initializeDataBase();
        initializeScreenElements();

    }


    //Инициализируем элементы экрана
    @Override
    public void initializeScreenElements() {
        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_FinalAddRelat:


                    case R.id.btn_CancelAddRelat:
                        finish();
                        break;
                }
            }
        };

        //Инициализация элементов
        etCustomerId = findViewById(R.id.et_IDRelatDoc);

        btnCommit = findViewById(R.id.btn_FinalAddRelat);
        btnCommit.setOnClickListener(oclBtn);

        btnCancel = findViewById(R.id.btn_CancelAddRelat);
        btnCancel.setOnClickListener(oclBtn);

        lnLoading = findViewById(R.id.ln_NUL);
    }




    //Метод который инициализирует базу данных
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().build();
    }

    @Override
    public void initializeList() {}


    private void makeToast(String text){
        Toast.makeText(ActivityDialogAddNewUser.this, text, Toast.LENGTH_SHORT).show();
    }




    //Async для загрузки юзеров с сервера
    class LoadingAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lnLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String id = etCustomerId.getText().toString();
            NetworkService.getInstance().
                    getUserApi().
                    getUserById(id).
                    enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            makeToast("Не удалось загрузить пользователя");
                        }
                    });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lnLoading.setVisibility(View.GONE);

        }
    }





}
