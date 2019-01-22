package com.example.dmitriy.emergencyassistant;

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

       //Фрагмент основного меню
       private static Fragment_NeedyMain fMain;

       //Фрагмент меню звонков
       private static Fragment_NeedyCalls fCalls;

       //Транзакция длдя фрагментов
       private static FragmentTransaction fTran;

       /*
       Переменная которая нужна для переключения между основными фрагментами
       main=true = оззначает что изначально установлена активность главного экрана
       */
       private static boolean main=true;

    //OnCreate
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy);

        //Листенер для кнопок
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 switch (v.getId()){

                 }
            }
        };

        //Метод который изначально устанавливает главный экран
        setFragment();
        seeLogs();
    }

    /*
    Метод который изначально устанавливает главный экран
    Он в общем полезен для определения нужного фрагмента
     */
    private void setFragment(){
        //Инициализируем фрагменты
        fMain=new Fragment_NeedyMain();
        fCalls=new Fragment_NeedyCalls();

        //Инициализируем транзакцию
        fTran=getSupportFragmentManager().beginTransaction();
        /*
        Проверяем переменную main
        если true, то ставим гланый экран
         */
        if(main){
            fTran.add(R.id.fragContNeedy, fMain);
            Log.i("LOG_TAG", "--- Set fragment main ---");}
        //Если false, то ставим экран звонков
        else{
            fTran.add(R.id.fragContNeedy, fCalls);
            Log.i("LOG_TAG", "--- Set fragment call ---");}
        /*
        Добавляем в бэкстэк для того что бы можно
        было вернуться к фрагменту после нажатия
        физической кнопки "назад"
         */
        fTran.addToBackStack(null);
        //Применяем транзакцию
        fTran.commit();
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
        fTran=getSupportFragmentManager().beginTransaction();
        if(main){
            fTran.replace(R.id.fragContNeedy, fMain);
            Log.i("LOG_TAG", "--- Set fragment main ---");
        }
        else {
            fTran.replace(R.id.fragContNeedy, fCalls);
            Log.i("LOG_TAG", "--- Set fragment call ---");
        }
        fTran.addToBackStack(null);
        fTran.commit();
        Log.i("LOG_TAG", "--- Fragment changed! ---");
    }


    /*
    Достаются загруженные данные из класса Needy
    Описание сигналов есть в этом классе
    В зависимости от выбранного значения, происходят разные события
     */
    @Override
    public void sendSos() {
        if(Needy.getSignalSOS()==0){
            Log.i("LOG_TAG", "--- Send signal to relatives and volunteers ---");}
        else if(Needy.getSignalSOS()==1){
            Log.i("LOG_TAG", "--- Send signal only relatives ---");}
        else if(Needy.getSignalSOS()==2){
            Log.i("LOG_TAG", "--- Send signal only volunteers ---");}
        Intent i=new Intent(this, Activity_Dialog_SendedSignal.class);
        startActivity(i);
    }

    @Override
    public void sendShop() {
        if(Needy.getSignalHelp()==0){
            Log.i("LOG_TAG", "--- Send signal to relatives and volunteers ---");}
        else if(Needy.getSignalHelp()==1){
            Log.i("LOG_TAG", "--- Send signal only relatives ---");}
        else if(Needy.getSignalHelp()==2){
            Log.i("LOG_TAG", "--- Send signal only volunteers ---");}
        Intent i=new Intent(this, Activity_Dialog_SendedSignal.class);
        startActivity(i);
    }

    @Override
    public void sendHouse() {
        if(Needy.getSignalHelp()==0){
            Log.i("LOG_TAG", "--- Send signal to relatives and volunteers ---");}
        else if(Needy.getSignalHelp()==1){
            Log.i("LOG_TAG", "--- Send signal only relatives ---");}
        else if(Needy.getSignalHelp()==2){
            Log.i("LOG_TAG", "--- Send signal only volunteers ---");}
        Intent i=new Intent(this, Activity_Dialog_SendedSignal.class);
        startActivity(i);
    }

    private void seeLogs(){
        //Выводим нужную нам информацию в логи
        Log.i("LOG_TAG", "--- Info ---");
        Log.i("LOG_TAG", "--- Check state: "+Needy.isCheckstate()+" ---");
        Log.i("LOG_TAG", "--- Signal SOS: "+Needy.getSignalSOS()+" ---");
        Log.i("LOG_TAG", "--- Signal HELP: "+Needy.getSignalHelp()+" ---");
        Log.i("LOG_TAG", "--- Signal STATE: "+Needy.getSignalState()+" ---");
    }


}
