/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 9:38 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/18/19 9:15 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.customer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityAboutApp;
import com.example.dmitriy.emergencyassistant.activities.dialogs.lists.ActivityDialogNumbers;
import com.example.dmitriy.emergencyassistant.activities.based.ActivityMain;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomer;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.tooltip.Tooltip;

/*
Фрмагмент настроек Needy
 */

public class FragmentCustomerSettings extends Fragment implements
        InterfaceDataBaseWork,
        InterfaceInitialize {


    //Элементы экрана

    //Информация о самом пользователе
    private TextView
            etSurname,
            etName,
            etMiddleName,
            etInfo,
            etNeedyId;

    //Кнопки на экране
    private Button
            btnDelete,
            btnHelpID,
            btnHelpState,
            btnCopyID,
            btnConfirm,
            btnNumbers,
            btnSocialHelp,
            btnAboutApp;

    //Элементы выборов сигнала
    private Button
            btnHelp0,
            btnHelp1,
            btnHelp2;

    //Кнопки для установки отслеживания состояния
    private Button
            btnStateYes,
            btnStateNo;

    //Индикаторы состояния функций
    private TextView
            tvCheckState,
            tvStateHelp;

    //View который используется на экране
    private View v;

    //Объект интерфейса для связи с активностью
    private InterfaceNeedySettings interfaceNeedySettings;

    //Объекты БД
    private DataBaseAppDatabase dataBase;
    private EntityUser profile;
    private EntityCustomer needy;


    //Без этой инициализации интерфейс для связи с активностью не будет работать
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interfaceNeedySettings = (InterfaceNeedySettings) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_customer_settings, container, false);
        //initializeDataBase();
        initializeScreenElements();
        //setState();
        return v;
    }



    @Override
    public void initializeScreenElements() {
        //Листенер для кнопок сохранения и удаления
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_Numbers:
                        startNumbers();
                        break;
                    case R.id.btn_DeleteProfileNeedy:
                        deleteProfile();
                        break;
                    case R.id.btn_SocialHelp:
                        startSocial();
                        break;
                    case R.id.btn_NeedyAboutApp:
                        startAbout();
                        break;
                    case R.id.btn_needy_settings_copy:
                        copyId();
                        break;
                    case R.id.btn_confirmSettings:
                        startMain();
                        break;

                }
            }
        };


        //Листенер для подсказок
        View.OnClickListener oclTips = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_needy_settings_id:
                        showTooltip(v, Gravity.BOTTOM, "Это ваш уникальный ID. \n \n" +
                                "Используйте его для того, что бы другие пользователи смогли " +
                                "вас найти. \n \n" +
                                "(Нажмите на сообщение чтобы закрыть его)");
                        break;
                    case R.id.btn_needysettings_statehelp:
                        showTooltip(v, Gravity.BOTTOM, "С помощью этой опции можно отслеживать " +
                                "состояние данного пользователя." +
                                "\n \n" +
                                "Опрос состояния здоровья проводится с 9:00 до 21:00, каждый день " +
                                "до отключения этой функции. " +
                                "Полученные данные отправляются всем подключённым пользователям. " +
                                "(Кроме соц. работников) " +
                                "\n \n" +
                                "(Нажмите на сообщение чтобы закрыть его)");
                        break;
                }
            }
        };


        //Листенер для переключателей
        View.OnClickListener oclState=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                switch (v.getId()){
                    case R.id.btn_state_no:
                        dataBase.dao_needy().setState(0);
                        tvCheckState.setText("Не отслеживается");
                        break;
                    case R.id.btn_state_yes:
                        dataBase.dao_needy().setState(1);
                        tvCheckState.setText("Отслеживается");
                        //interfaceNeedySettings.startService();
                        break;
                }
                 */
            }
        };

        //Загружаем настрокий
        //loadSettings();
        //Инициализация элементов экрана
        etSurname=v.findViewById(R.id.etSurname);
        //etSurname.setText(profile.getSurname());

        etName=v.findViewById(R.id.etName);
        //etName.setText(profile.getName());

        etMiddleName=v.findViewById(R.id.etMiddleName);
        //etMiddleName.setText(profile.getMiddlename());

        /*
        etInfo=v.findViewById(R.id.etInfo);
        etInfo.setText(needy.getInfo());
         */
        tvCheckState =v.findViewById(R.id.tv_StateCheck);

        btnNumbers =v.findViewById(R.id.btn_Numbers);
        btnNumbers.setOnClickListener(oclBtn);

        /*
        etNeedyId=v.findViewById(R.id.tv_NeedySettingsID);
        etNeedyId.setText(needy.getProfile_id());
         */

        btnDelete =v.findViewById(R.id.btn_DeleteProfileNeedy);
        btnDelete.setOnClickListener(oclBtn);

        btnSocialHelp=v.findViewById(R.id.btn_SocialHelp);
        btnSocialHelp.setOnClickListener(oclBtn);

        btnStateNo =v.findViewById(R.id.btn_state_no);
        btnStateNo.setOnClickListener(oclState);

        btnStateYes =v.findViewById(R.id.btn_state_yes);
        btnStateYes.setOnClickListener(oclState);

        btnHelpID = v.findViewById(R.id.btn_needy_settings_id);
        btnHelpID.setOnClickListener(oclTips);

        btnHelpState = v.findViewById(R.id.btn_needysettings_statehelp);
        btnHelpState.setOnClickListener(oclTips);

        btnAboutApp = v.findViewById(R.id.btn_NeedyAboutApp);
        btnAboutApp.setOnClickListener(oclBtn);

        btnCopyID = v.findViewById(R.id.btn_needy_settings_copy);
        btnCopyID.setOnClickListener(oclBtn);

        btnConfirm = v.findViewById(R.id.btn_confirmSettings);
        btnConfirm.setOnClickListener(oclBtn);
    }

    @Override
    public void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();

        //Инициализируем объект профиля
        /*
        profile=dataBase.dao_user().getProfile();
        needy=dataBase.dao_needy().getNeedy();
         */
    }

    @Override
    public void initializeList() {}



    /*
    Методы которые идут ниже - используются для управления в меню настроек
    Т.е. каждый метод выполняет свою функцию в настройках
     */

    //Методы управления на экране пользователя
    private void deleteProfile(){
        Intent main=new Intent(getContext(), ActivityMain.class);
        startActivity(main);
    }

    //Открываем окно с номерами телефонов
    private void startNumbers(){
        Intent numbers=new Intent(getContext(), ActivityDialogNumbers.class);
        startActivity(numbers);
    }

    //Открываем окно с информацией о соц. обслуживании
    private void startSocial(){
        /*
        if(dataBase.dao_needy_volunteer().getVolunteer() != null){
            Intent i = new Intent(getContext(), ActivityDialogSocialInfo.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(getContext(), ActivityDialogAddVolunteer.class);
            startActivity(i);
        }
         */
    }

    //Устанавливаем индикатор для состояния
    private void setState(){
        /*
        if(dataBase.dao_needy().getNeedy().getState_signal()==1){
            tvCheckState.setText("Отслеживается");

        }
        else if(dataBase.dao_needy().getNeedy().getState_signal()==0){
            tvCheckState.setText("Не отслеживается");
        }
         */
    }

    //Открываем окно с информацией о приложении
    private void startAbout(){
        Intent i = new Intent(getContext(), ActivityAboutApp.class);
        startActivity(i);
    }


    //Отдельный метод для быстрого отображения подсказки
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

    //Метод который выполняет копирование ID пользователя
    private void copyId(){
        /*
        ClipData clipData;

        ClipboardManager clipboardManager;
        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        String id = dataBase.dao_user().getProfile().getId();

        clipData = ClipData.newPlainText("id", id);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(getContext(),"ID был скопирован! ",Toast.LENGTH_SHORT).show();
         */
    }


    /*
    Метод который возвращает нас в активность Main
    После этого, Main проверяет какой сейчас пользователь активирован
    и открывает нужный раздел
    Грубо говоря - приложение перезапускается

    Метод используется для возвращения из меню настроек
     */
    private void startMain(){
        Intent i = new Intent(getContext(), ActivityMain.class);
        startActivity(i);
    }


    /*
    Интерфейс необходимый для связи с активностью
    Конкретно в этом случае запускается сервис отслеживания состояния
     */
    public interface InterfaceNeedySettings{
        void startService();
    }


}
