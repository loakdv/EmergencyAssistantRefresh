package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Activity_DoctorRelative extends AppCompatActivity implements Fragment_DoctorRelativeMain.onChangeDocFrag {

    //Переменная необходимая для смены основных фрагментов


    //Фрагменты основного "вида" и настроек
    private Fragment_DoctorRelativeMain fragmentMain;
    private Fragment_DoctorRelativeSettings fragmentSettings;

    //Транзакция для этих фрагментов
    private FragmentTransaction fragmentTransaction;

    private boolean main=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        //Инициализируем основные фрагменты
        fragmentMain=new Fragment_DoctorRelativeMain();
        fragmentSettings=new Fragment_DoctorRelativeSettings();

        //Устанавливаем фрагмент
        setFragment();
    }




    /*
    Метод определяющий какой фрагмент вставить в главный контейнер
    определяет это по переменной main
     */
    private void setFragment(){
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if(main){
            fragmentTransaction.add(R.id.frameDocMain, fragmentMain);
        }
        else {
            fragmentTransaction.add(R.id.frameDocMain, fragmentSettings);
        }
        fragmentTransaction.commit();

    }




    /*
    Метод необходимый для смены фрагмента
    в данном случае для смены основного
    фрагмента на фрагмент
     */
    @Override
    public void changeFragment() {
        main=!main;
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if(main){
            fragmentTransaction.replace(R.id.frameDocMain, fragmentMain);
        }
        else {
            fragmentTransaction.replace(R.id.frameDocMain, fragmentSettings);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




}
