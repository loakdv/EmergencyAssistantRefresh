/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 5:12 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityMain;
import com.example.dmitriy.emergencyassistant.core.LoginShell;
import com.example.dmitriy.emergencyassistant.helpers.HelperCreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Фрагмент для логирования в системе
 */

public class FragmentLoginEnter extends Fragment implements
        InterfaceInitialize {

    private static final String LOG_TAG = "LOGIN_SERVICE_FRAGMENT";

    //Кнопка входа в аккаунт
    private Button
            btnEnterLog,
            btnBack;

    private EditText
            etEnterEmail,
            etEnterPassword;
    private LinearLayout lnLoading;
    private View v;

    private LoginShell loginShell;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_login_enter, container, false);
        initializeScreenElements();
        return v;
    }



    @Override
    public void initializeScreenElements() {
        //Листенер нажатий
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_EnterLog:

                        HelperCreateProfile.EMAIL =etEnterEmail.getText().toString();
                        HelperCreateProfile.PASSWORD =etEnterPassword.getText().toString();

                        login(HelperCreateProfile.EMAIL, HelperCreateProfile.PASSWORD);
                        break;

                    case R.id.btn_login_enter_back:
                        getActivity().onBackPressed();
                        break;
                }
            }
        };

        //Инициализируем кнопку и припысываем листенер
        btnEnterLog =v.findViewById(R.id.btn_EnterLog);
        btnEnterLog.setOnClickListener(oclBtn);

        btnBack=v.findViewById(R.id.btn_login_enter_back);
        btnBack.setOnClickListener(oclBtn);

        etEnterEmail=v.findViewById(R.id.et_Enter_Email);
        etEnterPassword=v.findViewById(R.id.et_Enter_Password);

        lnLoading = v.findViewById(R.id.lnLEL);

        loginShell = new LoginShell(getContext());
    }


    /*
    Вызывается при нажатии кнопки авторизации
    Принимает в себя логин и пароль, и на их основе
    происходит сама авторизация
     */
    public void login(String login, String password){
        getUserFromServer(login, password);
    }

    //Получаем юзера с сервера
    private void getUserFromServer(String login, String profilePassword){
        GetUserAsync getUserAsync = new GetUserAsync(login, profilePassword);
        getUserAsync.doInBackground();
    }



    private void putUserInDatabase(User user){
        loginShell.putNewUserInDataBase(user);
    }


    //Отдельный метод для более быстрого и удобного создания тостов
    private void makeToast(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }


    /*
   Метод необходим для "перезапуска" приложения
   Вызывается в конце процесса регистрации/логина
    */
    private void startMain(){
        //Пользователь уже видел окно приветсвия, значит выполняем этот метод
        Intent main = new Intent(getContext(), ActivityMain.class);
        startActivity(main);
    }




    //Async для загрузки тасков с сервера, с дальнейшим перезапуском приложения
    class GetUserAsync extends AsyncTask<Void, Void, Void> {

        String name;
        String password;


        public GetUserAsync(String name, String password){
            this.name = name;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            lnLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            NetworkService.getInstance()
                    .getUserApi()
                    .getUserByName(name)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User gettingUser = response.body();

                            if(gettingUser != null){

                                putUserInDatabase(gettingUser);

                                if(password.equals(gettingUser.getPassword())){
                                    loginShell.putNewUserInDataBase(gettingUser); //Сначала добавляем юзера в базу данных
                                    loginShell.putNewCurrentUserNickname(gettingUser.getNickname()); //Затем делаем его основным юзером

                                    startMain();
                                }

                                else makeToast(getResources().getString(R.string.password_wrong));


                            }
                            else {
                                Log.d(LOG_TAG, "USER IS NULL!");
                                Toast.makeText(getContext(), "USER IS NULL!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getContext(), "ERROR!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(LOG_TAG, "REQUEST: "+call.request().toString());
                            Log.d(LOG_TAG, "IS EXECUTED: "+call.isExecuted());
                            Log.d(LOG_TAG, "IS CANCELED: "+call.isCanceled());
                            Log.d(LOG_TAG, t.getMessage());
                        }

                    });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            lnLoading.setVisibility(View.GONE);
        }
    }

}
