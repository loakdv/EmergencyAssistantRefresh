package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Activity_DoctorRelative extends AppCompatActivity implements Fragment_DoctorRelativeMain.onChangeDocFrag {

    //Переменная необходимая для смены основных фрагментов
    private boolean main=true;

    //Фрагменты основного "вида" и настроек
    Fragment_DoctorRelativeMain fMain;
    Fragment_DoctorRelativeSettings fSettings;

    //Транзакция для этих фрагментов
    FragmentTransaction fTran;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        //Инициализируем основные фрагменты
        fMain=new Fragment_DoctorRelativeMain();
        fSettings=new Fragment_DoctorRelativeSettings();

        //Устанавливаем фрагмент
        setFragment();
    }

    /*
    Метод определяющий какой фрагмент вставить в главный контейнер
    определяет это по переменной main
     */
    private void setFragment(){
        fTran=getSupportFragmentManager().beginTransaction();
        if(main){
            fTran.add(R.id.frameDocMain, fMain);
        }
        else {
            fTran.add(R.id.frameDocMain, fSettings);
        }
        fTran.commit();

    }


    /*
    Метод необходимый для смены фрагмента
    в данном случае для смены основного
    фрагмента на фрагмент
     */
    @Override
    public void changeFragment() {
        main=!main;
        fTran=getSupportFragmentManager().beginTransaction();
        if(main){
            fTran.replace(R.id.frameDocMain, fMain);
        }
        else {
            fTran.replace(R.id.frameDocMain, fSettings);
        }
        fTran.addToBackStack(null);
        fTran.commit();
    }

}
