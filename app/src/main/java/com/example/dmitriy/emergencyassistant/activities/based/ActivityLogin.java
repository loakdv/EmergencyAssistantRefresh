/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/30/19 3:26 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogWelcomeMenu;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginEnter;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginCreateRequest;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginFirstSelect;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Активность для создания аккаунта
Имплементируется   FragmentLoginFirstSelect.ChangeLoginFragment для того,
что-бы иметь связь с отображаемым фрагментом, и что-бы мы могли выполнять
системные методы из активности
*/

public class ActivityLogin extends AppCompatActivity implements
        InterfaceDataBaseWork {

    //Переменные которые нужны для доступа к файлам настроек раздела авторизации
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;

    //Фрагменты используемые в активности
    private FragmentLoginFirstSelect fragmentFirstSelect;
    private FragmentLoginEnter fragmentEnter;
    private FragmentLoginCreateRequest fragmentLoginCreateRequest;

    //Транзакция для смены фрагментов
    private FragmentTransaction fragmentTransaction;

    //Локальная база данных
    private DataBaseAppDatabase dataBase;

    /*
   Эти переменные изначально хранились в методе finishRegistration,
   но я решил их перенести в поля класса, и вынести их инициализацию
   в отдельный метод
   */






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initializeDataBase();
        initializeFragments();
        //Устанавливаем первый фрагмент
        setFirst();
        getPreferences();
    }


    //Метод который инициплизирует локальную БД
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(), DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().
                build();
    }




    //Отдельный метод для инициализации фрагментов
    private void initializeFragments() {
        fragmentFirstSelect = new FragmentLoginFirstSelect();
        fragmentEnter = new FragmentLoginEnter();
        fragmentLoginCreateRequest = new FragmentLoginCreateRequest();
    }



    /*
  Этот метод выполняет свою работу при первом запуске приложения
  Если приложение запущено в первый раз - открываем окно приветствия
   */
    private void getPreferences(){
        //Получаем нужный SharedPreferences
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        //Получаем нужную нам переменную
        boolean isFirstLogin = loginPreferences.getBoolean("isFirstStartConfirmed", false);

        /*
        Если false (т.е. приложение запущено в первый раз),
        то показываем окно приветствия
         */
        if (!isFirstLogin){
            startWelcomeMenu();
        }
    }







    /*
    Метод необходим для "перезапуска" приложения
    Вызывается в конце процесса регистрации/логина
     */
    private void startMain(boolean isNeedy){
        //Пользователь уже видел окно приветсвия, значит выполняем этот метод

        if(isNeedy){
            Intent i = new Intent(getApplicationContext(), ActivityCustomerSettings.class);
            startActivity(i);
        }
        else {
            Intent main = new Intent(this, ActivityMain.class);
            startActivity(main);
        }

    }





    //Метод который показывает окно с приветствием
    private void startWelcomeMenu(){
        Intent i = new Intent(this, ActivityDialogWelcomeMenu.class);
        startActivity(i);
        setPreferencesConfirmed();
    }




    /*
    Устанавливаем переменную "первого запуска" правдивой
    При следующем запуске этой активности, приложение будет знать
    что пользователь уже видел это окно
     */
    private void setPreferencesConfirmed(){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putBoolean("isFirstStartConfirmed", true);
        editor.apply();
    }





    public void login(String login, String password){
        getUserFromServer(login, password);
    }

    private void getUserFromServer(String login, String profilePassword){
        GetUserAsync getUserAsync = new GetUserAsync(login, profilePassword);
        getUserAsync.doInBackground();
    }



    private boolean checkUserOnNull(User user){
        if(user == null)
            return false;

        else return true;
    }




    //Имплементированные методы смены рабочего фрагмента

    /*
    Все эти методы я убрал в самый низ, т.к. они однотипные и в принципе выполняют
    одну и ту же роль
    Так они не будут мешаться
     */

    //Метод устанавливает самый первый экран активности
    public void setFirst() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentFirstSelect);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    //Вход в аккаунт
    public void setEnter() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentEnter);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //Окно создания заявки на соц. обслуживание
    public void setRequest() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentLoginCreateRequest);
        fragmentTransaction.commit();
    }



    //Отдельный метод для более быстрого и удобного создания тостов
    private void makeToast(String text){
        Toast.makeText(ActivityLogin.this, text, Toast.LENGTH_SHORT).show();
    }





    //Async для загрузки тасков с сервера
    class GetUserAsync extends AsyncTask<Void, Void, Void> {

        String name;
        String password;


        public GetUserAsync(String name, String password){
            this.name = name;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            NetworkService.getInstance()
                    .getUserApi()
                    .getUserByName(new User(name, password))
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User gettingUser = response.body();

                            if(checkUserOnNull(gettingUser)){
                                Log.d(LOG_TAG, "SUCCESSFUL!");
                                Toast.makeText(getApplicationContext(), "SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Log.d(LOG_TAG, "USER IS NULL!");
                                Toast.makeText(getApplicationContext(), "USER IS NULL!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_SHORT).show();
                        }
                    });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }




}
