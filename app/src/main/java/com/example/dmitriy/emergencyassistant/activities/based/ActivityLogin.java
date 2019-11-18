/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/22/19 10:51 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogWelcomeMenu;
import com.example.dmitriy.emergencyassistant.core.LoginShell;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginEnter;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginCreateRequest;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginFirstSelect;
import com.example.dmitriy.emergencyassistant.R;

/*
Активность для создания аккаунта
Имплементируется   FragmentLoginFirstSelect.ChangeLoginFragment для того,
что-бы иметь связь с отображаемым фрагментом, и что-бы мы могли выполнять
системные методы из активности
*/

public class ActivityLogin extends AppCompatActivity{

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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeFragments();
        setFirst(); //Устанавливаем первый фрагмент
        checkFirstVisit(); //Юзер зашёл в приложение первый раз или нет
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
    private void checkFirstVisit(){
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


}
