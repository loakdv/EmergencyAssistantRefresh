package com.example.dmitriy.emergencyassistant;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Activity_Needy extends AppCompatActivity implements Fragment_NeedyMain.onSomeEventListener {

    /*
      Данное активити используется для "пациента"
    */

    NotificationManager notificationManager;





    Fragment_NeedyMain fragmentMain;
    Fragment_NeedyCalls fragmentCalls;
    FragmentTransaction fragmentTransaction;

    private boolean main=true;
    private boolean checkState;



    //OnCreate
    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy);
        notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        setFragment();
        getFromIntent();
    }




    /*
    Метод который изначально устанавливает главный экран
    Он в общем полезен для определения нужного фрагмента
     */
    private void setFragment(){
        fragmentMain=new Fragment_NeedyMain();
        fragmentCalls=new Fragment_NeedyCalls();
        fragmentTransaction=getSupportFragmentManager().beginTransaction();

        /*
        Проверяем переменную main
        если true, то ставим гланый экран
         */
        if(main){
            fragmentTransaction.add(R.id.fragContNeedy, fragmentMain); }
        //Если false, то ставим экран звонков
        else{
            fragmentTransaction.add(R.id.fragContNeedy, fragmentCalls); }

        /*
        Добавляем в бэкстэк для того что бы можно
        было вернуться к фрагменту после нажатия
        физической кнопки "назад"
         */
        fragmentTransaction.addToBackStack(null);
        //Применяем транзакцию
        fragmentTransaction.commit();

    }




    /*
    Метод используемый для смены фрагмента во время
    работы самой активности
    Сначала он ставит противоположное значение переменной main
    и уже от этой переменной отталкивается в дальнейшем
    (Происходит почти тоже самое что в методе setFragment, только
    используется replace)
     */

    /*
    Методы интерфейса, что бы можно было связаться с этой активностью из фрагментов
    Как только фрагмент использует эвент, то он переходит к этой активности
    и тут уже срабатывают нужные методы;
     */
    @Override
    public void changeFrag() {
        main=!main;
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if(main){
            fragmentTransaction.replace(R.id.fragContNeedy, fragmentMain);
        }
        else {
            fragmentTransaction.replace(R.id.fragContNeedy, fragmentCalls);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    /*
    Достаются загруженные данные из класса Needy
    Описание сигналов есть в этом классе
    В зависимости от выбранного значения, происходят разные события
     */
    @Override
    public void sendSos() {
        Intent signal=new Intent(this,
                Activity_Dialog_SendedSignal.class);
        startActivity(signal);
    }




    @Override
    public void sendShop() {
        Intent signal=new Intent(this,
                Activity_Dialog_SendedSignal.class);
        startActivity(signal);
    }




    @Override
    public void sendHouse() {
        Intent signal=new Intent(this,
                Activity_Dialog_SendedSignal.class);
        startActivity(signal);
    }




    @Override
    public void checkState() {
        Intent state=new Intent(this,
                Activity_Dialog_StateCheck.class);
        startActivity(state);
    }




    private void startCheckState(){
        Intent state=new Intent(this, Activity_Dialog_StateCheck.class);
        startActivity(state);
        checkState=false;
    }




    private void getFromIntent(){
        boolean extraCheckState=getIntent().getBooleanExtra("check_state", false);
        checkState=extraCheckState;
        if(checkState){
            startCheckState();
        }

    }







}
