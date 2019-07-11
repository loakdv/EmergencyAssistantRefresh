/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogWelcomeMenu;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginEnter;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginCreateAccount;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginCreateRequest;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginFirstSelect;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginNeedy;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginRelative;
import com.example.dmitriy.emergencyassistant.fragments.login.FragmentLoginVolunteer;
import com.example.dmitriy.emergencyassistant.helpers.HelperCreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;

/*
Активность для создания аккаунта
*/

public class ActivityLogin extends AppCompatActivity implements
        FragmentLoginFirstSelect.ChangeLoginFragment, InterfaceDataBaseWork {


    /*
    Переменные которые нужны для доступа к файлам настроек раздела авторизации
     */
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private SharedPreferences loginPreferences;

    /*
    Фрагменты используемые в активности
     */
    private FragmentLoginFirstSelect fragmentFirstSelect;
    private FragmentLoginEnter fragmentEnter;
    private FragmentLoginCreateAccount fragmentCreate;
    private FragmentLoginNeedy fragmentNeedy;
    private FragmentLoginVolunteer fragmentVolunteer;
    private FragmentLoginCreateRequest fragmentLoginCreateRequest;

    /*
    Транзакция для смены фрагментов
     */
    private FragmentTransaction fragmentTransaction;

    /*
    Локальная база данных
     */
    private DataBaseAppDatabase dataBase;


    /*
   Эти переменные изначально хранились в методе finishRegistration,
   но я решил их перенести в поля класса, и вынести их инициализацию
   в отдельный метод
   */

    //Достаём данные из хелпера
    //Тип профиля
    private int profileType = HelperCreateProfile.TYPE;


    //Основные сведения пользователя
    private String profileName = HelperCreateProfile.NAME;
    private String profileSurname = HelperCreateProfile.SURNAME;
    private String profileMiddlename = HelperCreateProfile.MIDDLENAME;
    private String profileInfo = HelperCreateProfile.INFO;

    //Данные для входа/регистрации
    private String profileEmail = HelperCreateProfile.EMAIL;
    private String profilePassword = HelperCreateProfile.PASSWORD;

    private byte[] profilePhoto = HelperCreateProfile.PHOTO;

    private String profileId;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeDataBase();
        initializeFragments();

        //Устанавливаем первый фрагмент
        setFirst();

        getPreferences();

        getIntentMessages();

    }




    //Метод который инициплизирует локальную БД
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }

    @Override
    public void initializeList() {}


    private void initializeFragments() {
        //Инициализация фрагментов
        fragmentFirstSelect = new FragmentLoginFirstSelect();
        fragmentEnter = new FragmentLoginEnter();
        fragmentCreate = new FragmentLoginCreateAccount();
        fragmentNeedy = new FragmentLoginNeedy();
        fragmentVolunteer = new FragmentLoginVolunteer();
        fragmentLoginCreateRequest = new FragmentLoginCreateRequest();
    }


    /*
  Этот метод выполняет свою работу при первом запуске приложения
  Если приложение запущено в первый раз - открываем окно приветствия
   */
    private void getPreferences(){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        boolean isFirstLogin = loginPreferences.getBoolean("isFirstStartConfirmed", false);

        if (!isFirstLogin){
            startWelcomeMenu();
        }
    }


    private void getIntentMessages(){
        boolean isFastUser = getIntent().getBooleanExtra("isFastUser", false);
        if (isFastUser){
            String login = getIntent().getStringExtra("fastEmail");
            String password = getIntent().getStringExtra("fastPassword");
            //login(login, password);
        }
    }





    /*
    Метод необходим для "перезапуска" приложения
    Вызывается в конце процесса регистрации/логина
     */
    private void startMain(boolean isNeedy){
        setPreferencesConfirmed();

        if(isNeedy){
            Intent i = new Intent(getApplicationContext(), ActivityCustomerSettings.class);
            startActivity(i);
        }
        else {
            Intent main = new Intent(this, ActivityMain.class);
            startActivity(main);
        }

    }





    /*
    Метод который инициализирует переменные взятые из хелпера
    Вынес это всё в отдельный метод, что бы не нагромождать это
    всё в другом методе
     */
    private void initializeRegistrationFields(){

        profileType = HelperCreateProfile.TYPE;

        profileName = HelperCreateProfile.NAME;
        profileSurname = HelperCreateProfile.SURNAME;
        profileMiddlename = HelperCreateProfile.MIDDLENAME;
        profileInfo = HelperCreateProfile.INFO;

        profileEmail = HelperCreateProfile.EMAIL;
        profilePassword = HelperCreateProfile.PASSWORD;

        profilePhoto = HelperCreateProfile.PHOTO;

    }









    private void startWelcomeMenu(){
        Intent i = new Intent(this, ActivityDialogWelcomeMenu.class);
        startActivity(i);
    }





    /*
    Устанавливаем переменную "первого запуска" правдивой
     */
    private void setPreferencesConfirmed(){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putBoolean("isFirstStartConfirmed", true);
        editor.apply();
    }






    /*
    После вызова этого метода, засчет переменной
    мы определяем что делать дальше - логинимся,
    или же создаём новый профиль
     */
    @Override
    public void continueLogin(boolean login) {

        initializeRegistrationFields();

        if(!login){
            //registration(profileEmail, profilePassword);
        }
        else {
            //login(profileEmail, profilePassword);
        }
    }




    //Имплементированные методы смены рабочего фрагмента

    /*
    Все эти методы я убрал в самый низ, т.к. они однотипные и в принципе выполняют
    одну и ту же роль

    Так они не будут мешаться
     */
    @Override
    public void setFirst() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentFirstSelect);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void setCreate() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentCreate);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void setEnter() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentEnter);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void setNeedy() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentNeedy);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void setRelative() {
    }




    @Override
    public void setVolun() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentVolunteer);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void setRequest() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentLoginCreateRequest);
        fragmentTransaction.commit();
    }


    //Отдельный метод для более быстрого и удобного создания тостов
    private void makeToast(String text){
        //Если всё хорошо, продолжаем регистрацию
        Toast.makeText(ActivityLogin.this, text, Toast.LENGTH_SHORT).show();
    }




}
