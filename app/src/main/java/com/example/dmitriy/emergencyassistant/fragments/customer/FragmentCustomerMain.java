package com.example.dmitriy.emergencyassistant.fragments.customer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityCustomerSettings;
import com.example.dmitriy.emergencyassistant.activities.Dialogs.Lists.ActivityDialogSelectTask;
import com.example.dmitriy.emergencyassistant.elements.ElementStateSelect;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

/*
Фрмагмент основного интерфейса Needy с большими кнопками
 */

public class FragmentCustomerMain extends Fragment implements ActivityDialogSelectTask.OnSelectItem {

    private FragmentTransaction fTran;

    private DataBaseAppDatabase dataBase;

    //Основные кнопки на главном экране "пациента"
    private Button btnCalls, btnSos, btnHome, btnShop, btn_State, btnSettings;
    private LinearLayout ln_Buttons;


    //Создаём интерфейс для связи с активностью "пациента"
    public interface onSomeEventListener {
        //Методы которые должны выполниться внутри активности
         void changeFrag();
         void sendSos();
         void sendHelpSignal(int type);
         void checkState();

    }

    //Создаём экземпляр интерфейса
    private onSomeEventListener someEventListener;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_needymain, container, false);

        //Инициализация кнопок
        btnCalls=v.findViewById(R.id.btnCall);
        btnSos=v.findViewById(R.id.btnSOS);
        btnHome=v.findViewById(R.id.btnHome);
        btnShop=v.findViewById(R.id.btnShop);
        btn_State=v.findViewById(R.id.btn_CheckState);
        btnSettings = v.findViewById(R.id.btn_Needy_Settings);


        //Листенер кнопок
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //При нажатии на кнопку срабатывают методы в интерфейсе
                    //Которые потом срабатывают в активности
                    case R.id.btnCall:
                        someEventListener.changeFrag();
                        break;
                    case R.id.btnSOS:
                        someEventListener.sendSos();
                        break;
                    case R.id.btnHome:

                        seeTasksWindow(0);
                        //someEventListener.sendHelpSignal(0);
                        break;
                    case R.id.btnShop:
                        seeTasksWindow(1);
                        //someEventListener.sendHelpSignal(1);
                        break;
                    case R.id.btn_CheckState:
                        someEventListener.checkState();
                        break;
                    case R.id.btn_Needy_Settings:
                        openSettings();
                        break;
                }
            }
        };

        //Установка листенеров
        btnCalls.setOnClickListener(oclBtn);
        btnShop.setOnClickListener(oclBtn);
        btnHome.setOnClickListener(oclBtn);
        btnSos.setOnClickListener(oclBtn);
        btn_State.setOnClickListener(oclBtn);
        btnSettings.setOnClickListener(oclBtn);

        ln_Buttons=v.findViewById(R.id.ln_Needy_HelpButtons);

        initializeDataBase();
        checkVisibleButtons();

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Присваиваем листенеру эвента сонтекст(активность), которая должна исполнять эвент
        someEventListener = (onSomeEventListener) context;
    }



    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }

    private void checkVisibleButtons(){
        if(dataBase.dao_needy_volunteer().getAll().isEmpty()){
            ln_Buttons.setVisibility(View.GONE);
        }
    }

    private void openSettings(){
        Intent i = new Intent(getContext(), ActivityCustomerSettings.class);
        startActivity(i);
    }

    private void seeTasksWindow(int type){
        Intent i = new Intent(getContext(), ActivityDialogSelectTask.class);
        i.putExtra("select_type", type);
        startActivity(i);
    }

    @Override
    public void selectItem(ElementStateSelect element) {
        someEventListener.sendHelpSignal(element.getType());
    }
}
