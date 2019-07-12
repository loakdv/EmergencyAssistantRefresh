/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.infoblocks;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.tooltip.Tooltip;

//Фрагмент с блоками состояния(прогрессбарами)

@SuppressLint("ValidFragment")
public class FragmentStateInfo extends Fragment implements InterfaceInitialize,
        InterfaceDataBaseWork {


    //Элементы экрана
    private ProgressBar
            pb9,
            pb12,
            pb15,
            pb18,
            pb21;

    private Button btnHelp;

    //View используемый на экране
    private View v;




    //Передаваемый в интенте ID
    private String selectedId;

    //Локальная БД
    private DataBaseAppDatabase dataBase;

    @SuppressLint("ValidFragment")
    public FragmentStateInfo(String id){
        this.selectedId=id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_see_state, container, false);

        initializeDataBase();
        initializeScreenElements();

        return v;
    }



    /*
    Метод устанавливает для каждого ProgressBar'a
    значения из массива states
     */
    private void setStatesNumbers(int[] states){
        pb9.setProgress(states[0]);
        pb12.setProgress(states[1]);
        pb15.setProgress(states[2]);
        pb18.setProgress(states[3]);
        pb21.setProgress(states[4]);
    }


    @Override
    public void initializeScreenElements() {
        pb9 =v.findViewById(R.id.progressBar_9);
        pb12 =v.findViewById(R.id.progressBar12);
        pb15 =v.findViewById(R.id.progressBar15);
        pb18 =v.findViewById(R.id.progressBar18);
        pb21 =v.findViewById(R.id.progressBar21);

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_seestate_help:
                        showTooltip(v, Gravity.BOTTOM, "Здесь будет отображаться " +
                                "состояние пользователя на текущую дату." +
                                "\n \n" +
                                "Данные обновляются 5 раз, с 9:00 до 21:00." +
                                "\n \n" +
                                "Если данные не обновляются или не появляются вовсе, " +
                                "то скорее всего пользователь ещё не вснёс данные о состоянии, " +
                                "или же у него отключена функция отслеживания состояния." +
                                "\n \n" +
                                "(Нажмите на сообщение чтобы закрыть его)");
                        break;
                }
            }
        };

        btnHelp=v.findViewById(R.id.btn_seestate_help);
        btnHelp.setOnClickListener(oclBtn);
    }

    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }

    @Override
    public void initializeList() {}



    private void showTooltip(View v, int gravity, String text){

        Tooltip tooltip = new Tooltip.Builder(v).
                setText(text).
                setTextColor(Color.WHITE).
                setGravity(gravity).
                setDismissOnClick(true).
                setBackgroundColor(Color.BLUE).
                setCornerRadius(10f).
                show();

    }


}
