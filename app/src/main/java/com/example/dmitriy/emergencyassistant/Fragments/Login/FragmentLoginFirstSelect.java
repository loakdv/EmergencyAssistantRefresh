package com.example.dmitriy.emergencyassistant.Fragments.Login;

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

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.ActivityDialogOrganizationsList;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.ActivityDialogWelcomeMenu;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists.ActivityDialogFastUserSelect;
import com.example.dmitriy.emergencyassistant.R;
import com.tooltip.Tooltip;

/*
Фрагмент первого выбора (создание аккаунта или регистрация)
     */

public class FragmentLoginFirstSelect extends Fragment {



    /*
    Интерфейс который будет связывать все фрагменты логина
      Также нужен для связи с основной активностью ActivityLogin
     */
    public interface ChangeLoginFragment {
        void setVolun();
        void setNeedy();
        void setRelative();
        void setFirst();
        void setEnter();
        void setCreate();
        void setRequest();

        void continueLogin(boolean login);
    }


    //объявляем созданный интерфейс
    private ChangeLoginFragment changeLoginFragment;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Инициализируем интерфейс
        changeLoginFragment =(ChangeLoginFragment) context;
    }



    //Кнопки создания аккаунта и авторизации, назад
    private Button btn_CreateNewAccount;
    private Button btn_Autorization;
    private Button btnHelp;
    private Button btnAboutApp;
    private Button btnCreateRequest;
    private Button btnOrganizations;
    private Button btnFastUsers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login_firstselect, container, false);
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               switch (v.getId()){
                   case R.id.btn_CreateNewAccount:
                       //Вызываем метод из интерфейса
                       changeLoginFragment.setCreate();
                       break;
                   case R.id.btn_Autorization:
                       //Вызываем метод из интерфейса
                       changeLoginFragment.setEnter();
                       break;
                   case R.id.btn_Login_FirstSelect_Help:
                       String text= "В этом меню вы можете создать новый профиль или" +
                               " авторизоваться в уже имеющемся. \n" +
                               "(Нажмите на сообщение, что бы его закрыть)";
                       showTooltip(v, Gravity.TOP, text);
                       break;
                   case R.id.btn_login_fsel_aboutapp:
                       startAboutApp();
                       break;
                   case R.id.btn_CreateRequest:
                       changeLoginFragment.setRequest();
                       break;
                   case R.id.btn_Login_Organizations:
                       seeOrganizations();
                       break;
                   case R.id.btn_ready_users:
                       seeFastUsers();
                       break;
               }
            }
        };


        //Инициализация кнопок и присвоение листенера
        btn_Autorization=v.findViewById(R.id.btn_Autorization);
        btn_Autorization.setOnClickListener(oclBtn);
        btn_CreateNewAccount=v.findViewById(R.id.btn_CreateNewAccount);
        btn_CreateNewAccount.setOnClickListener(oclBtn);
        btnHelp = v.findViewById(R.id.btn_Login_FirstSelect_Help);
        btnHelp.setOnClickListener(oclBtn);

        btnCreateRequest = v.findViewById(R.id.btn_CreateRequest);
        btnCreateRequest.setOnClickListener(oclBtn);

        btnOrganizations = v.findViewById(R.id.btn_Login_Organizations);
        btnOrganizations.setOnClickListener(oclBtn);

        btnAboutApp = v.findViewById(R.id.btn_login_fsel_aboutapp);
        btnAboutApp.setOnClickListener(oclBtn);

        btnFastUsers = v.findViewById(R.id.btn_ready_users);
        btnFastUsers.setOnClickListener(oclBtn);

        return v;
    }


    private void showTooltip(View v, int gravity, String text){
        Button btn = (Button) v;

        Tooltip tooltip = new Tooltip.Builder(btn).
                setText(text).
                setTextColor(Color.WHITE).
                setGravity(gravity).
                setDismissOnClick(true).
                setBackgroundColor(R.drawable.lout_btn_blue).
                setCornerRadius(10f).
                show();

    }

    private void startAboutApp(){
        Intent i = new Intent(getContext(), ActivityDialogWelcomeMenu.class);
        startActivity(i);
    }

    private void seeOrganizations(){
        Intent i = new Intent(getContext(), ActivityDialogOrganizationsList.class);
        startActivity(i);
    }

    private void seeFastUsers(){
        Intent i = new Intent(getContext(), ActivityDialogFastUserSelect.class);
        startActivity(i);
    }


}
