/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 9:38 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/18/19 8:56 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityLogin;
import com.example.dmitriy.emergencyassistant.adapters.login.AdapterLoginFastUsers;
import com.example.dmitriy.emergencyassistant.elements.ElementFastUser;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;

import java.util.ArrayList;
import java.util.List;

/*
Активность нужна по большей степени для прототиного варианта приложения
Она позволяет быстро залогиниться за какого-нибудь юзера

 */
public class ActivityDialogFastUserSelect extends AppCompatActivity implements
        AdapterLoginFastUsers.CallBack,
        InterfaceInitialize {

    //Элементы экрана
    private RecyclerView rvUsers;
    private Button btnExit;

    //элементы нужные для списка
    private List<ElementFastUser> listUsers = new ArrayList<ElementFastUser>();
    private AdapterLoginFastUsers adapterLoginFastUsers;




    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fastuserselect);
        initializeScreenElements();
        initializeList();
        initializeRecycleView();

    }



    @Override
    public void initializeScreenElements() {
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_ExitUsers:
                        finish();
                        break;
                }
            }
        };
        btnExit = findViewById(R.id.btn_ExitUsers);
        btnExit.setOnClickListener(oclBtn);
    }



    /*
    Инициализируем список с "быстрыми" юзерами
    В дальнейшем эти пользователи будут загружаться
    с сервера
    */
    private void initializeList(){
        listUsers.add(new ElementFastUser("Алексеев Иван Евгеньевич", "needy1@mail.com", "111111","", "Нуждающийся в помощи"));
        listUsers.add(new ElementFastUser("Сидоров Алексей Евгеньевич", "needy2@mail.com", "111111","", "Нуждающийся в помощи"));
        listUsers.add(new ElementFastUser("Сергиенко Мария Владимировна", "volunteer1@gmail.com", "111111","", "Соц. работник"));
        listUsers.add(new ElementFastUser("Фадеев Иван Сергеевич", "volunteer2@gmail.com", "111111","", "Соц. работник"));

    }



    //Инициализируем элемент со списком
    private void initializeRecycleView(){
        rvUsers = findViewById(R.id.rv_fastUsers);
        adapterLoginFastUsers = new AdapterLoginFastUsers(getApplicationContext(), listUsers, this);
        rvUsers.setAdapter(adapterLoginFastUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    //Реагируем на выбор юзера
    @Override
    public void onUserSelected(ElementFastUser elementFastUser) {
        loginUser(elementFastUser.getEmail(), elementFastUser.getPassword());
    }




    //Открываем активность с логином и передаём нужные данные
    private void loginUser(String email, String password){
        Intent i = new Intent(getApplicationContext(), ActivityLogin.class);
        i.putExtra("isFastUser", true);
        i.putExtra("fastEmail", email);
        i.putExtra("fastPassword", password);
        startActivity(i);
    }


}
