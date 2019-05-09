package com.example.dmitriy.emergencyassistant.Fragments.Needy;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Activities.Based.Activity_AboutApp;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds.Activity_Dialog_AddVolunteer;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.Activity_SeeSocialInfo;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists.Activity_Dialog_Numbers;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists.Activity_Dialog_Users;
import com.example.dmitriy.emergencyassistant.Activities.Based.Activity_Main;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.example.dmitriy.emergencyassistant.Services.Service_AlarmState;
import com.example.dmitriy.emergencyassistant.Services.Service_BackGround;
import com.google.firebase.auth.FirebaseAuth;
import com.tooltip.Tooltip;

/*
Фрмагмент настроек Needy
 */

public class Fragment_NeedySettings extends Fragment {


    private FirebaseAuth mAuth;


    //Элементы экрана
    private TextView etSurname;
    private TextView etName;
    private TextView etMiddleName;
    private TextView etInfo;
    private TextView etNeedyId;

    private Button btn_Delete;
    private Button btnHelpID;
    private Button btnHelpState;
    private Button btnCopyID;

    public interface InterfaceNeedySettings{
        void startService();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interfaceNeedySettings = (InterfaceNeedySettings) context;
    }

    /*
        Кнопки для выбора фрагмента
        Фрагменты для добавления номеров, врачей, родственников
         */
    private Button btn_Numbers;
    private Button btn_Relatives;
    private Button btnSocialHelp;
    private Button btnAboutApp;


    private InterfaceNeedySettings interfaceNeedySettings;

    //Элементы выборов сигнала
    private Button btn_Help0, btn_Help1, btn_Help2;

    private Button btn_stateYes, btn_stateNo;
    private TextView tv_CheckState;

    private TextView tv_stateHelp;


    private DataBase_AppDatabase dataBase;

    private Entity_Profile profile;

    private Entity_Needy needy;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_needysettings, container, false);

        mAuth=FirebaseAuth.getInstance();

        initializeDataBase();



        //Листенер для кнопок сохранения и удаления
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_Numbers:
                        startNumbers();
                        break;
                    case R.id.btn_Profiles:
                        startRelatives();
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

                }
            }
        };


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



        View.OnClickListener oclState=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_state_no:
                        dataBase.dao_needy().setState(0);
                        tv_CheckState.setText("Не отслеживается");
                        break;
                    case R.id.btn_state_yes:
                        dataBase.dao_needy().setState(1);
                        tv_CheckState.setText("Отслеживается");

                        interfaceNeedySettings.startService();

                        break;
                }
            }
        };

        //Загружаем настрокий
        //loadSettings();
        //Инициализация элементов экрана
        etSurname=v.findViewById(R.id.etSurname);
        etSurname.setText(profile.getSurname());

        etName=v.findViewById(R.id.etName);
        etName.setText(profile.getName());

        etMiddleName=v.findViewById(R.id.etMiddleName);
        etMiddleName.setText(profile.getMiddlename());

        etInfo=v.findViewById(R.id.etInfo);
        etInfo.setText(needy.getInfo());

        btn_Numbers=v.findViewById(R.id.btn_Numbers);
        btn_Numbers.setOnClickListener(oclBtn);

        btn_Relatives=v.findViewById(R.id.btn_Profiles);
        btn_Relatives.setOnClickListener(oclBtn);

        etNeedyId=v.findViewById(R.id.tv_NeedySettingsID);
        etNeedyId.setText(needy.getProfile_id());



        btn_Delete=v.findViewById(R.id.btn_DeleteProfileNeedy);
        btn_Delete.setOnClickListener(oclBtn);

        btnSocialHelp=v.findViewById(R.id.btn_SocialHelp);
        btnSocialHelp.setOnClickListener(oclBtn);

        btn_stateNo=v.findViewById(R.id.btn_state_no);
        btn_stateNo.setOnClickListener(oclState);
        btn_stateYes=v.findViewById(R.id.btn_state_yes);
        btn_stateYes.setOnClickListener(oclState);
        tv_CheckState=v.findViewById(R.id.tv_StateCheck);

        btnHelpID = v.findViewById(R.id.btn_needy_settings_id);
        btnHelpID.setOnClickListener(oclTips);

        btnHelpState = v.findViewById(R.id.btn_needysettings_statehelp);
        btnHelpState.setOnClickListener(oclTips);

        btnAboutApp = v.findViewById(R.id.btn_NeedyAboutApp);
        btnAboutApp.setOnClickListener(oclBtn);

        btnCopyID = v.findViewById(R.id.btn_needy_settings_copy);
        btnCopyID.setOnClickListener(oclBtn);
        setState();
        return v;
    }



    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_profile().getProfile();
        needy=dataBase.dao_needy().getNeedy();
    }

    private void deleteProfile(){
        mAuth.signOut();
        dataBase.dao_profile().delete(profile);
        Intent main=new Intent(getContext(), Activity_Main.class);
        startActivity(main);
    }


    private void startNumbers(){
        Intent numbers=new Intent(getContext(), Activity_Dialog_Numbers.class);
        startActivity(numbers);
    }

    private void startRelatives(){
        Intent relatives=new Intent(getContext(), Activity_Dialog_Users.class);
        startActivity(relatives);
    }

    private void startSocial(){
        if(dataBase.dao_needy_volunteer().getVolunteer() != null){
            Intent i = new Intent(getContext(), Activity_SeeSocialInfo.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(getContext(), Activity_Dialog_AddVolunteer.class);
            startActivity(i);
        }


    }




    private void setState(){
        if(dataBase.dao_needy().getNeedy().getState_signal()==1){
            tv_CheckState.setText("Отслеживается");

        }
        else if(dataBase.dao_needy().getNeedy().getState_signal()==0){
            tv_CheckState.setText("Не отслеживается");
        }
    }



    private void startAbout(){
        Intent i = new Intent(getContext(), Activity_AboutApp.class);
        startActivity(i);
    }



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

    private void copyId(){

        ClipData clipData;

        ClipboardManager clipboardManager;
        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        String id = dataBase.dao_profile().getProfile().getId();

        clipData = ClipData.newPlainText("id", id);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(getContext(),"ID был скопирован! ",Toast.LENGTH_SHORT).show();
    }


}
