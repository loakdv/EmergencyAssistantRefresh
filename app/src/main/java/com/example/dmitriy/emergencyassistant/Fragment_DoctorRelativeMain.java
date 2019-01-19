package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment_DoctorRelativeMain extends Fragment {





    /*
    Этот интерфейс имплементируется активностью доктора
    Он необходим для смены рабочего фрагмента
     */
    public interface onChangeDocFrag{
       void changeFragment();
    }



    //Объект интерфеся для смены рабочего фрагмента
    onChangeDocFrag changeFrag;



    //Инициализируем объект интерфейчас при присоединении
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeFrag=(onChangeDocFrag)context;
    }



    //Работа с фрагментом состояния
    Fragment_SeeState fSeeState;
    FragmentTransaction fChildTranState;
    FragmentManager fChildManState;



    //Фрагменты заметок
    Fragment_SeeNotes fSeeNotes;
    FragmentTransaction fChildTranNotes;
    FragmentManager fChildManNotes;


    //Фрагмент выбора пациентов/пользователей
    Fragment_NeedyList fNeedyList;
    FragmentTransaction fChildTranList;
    FragmentManager fChildManList;


    //Фрагмент информации о пользователе
    Fragment_NeedyInfo fNeedyInfo;
    FragmentTransaction fChildTranInfo;
    FragmentManager fChildManInfo;


    //Фрагмент с фото в левом меню
    Fragment_TopPhoto fTopPhoto;
    FragmentTransaction fChildTranTopPhoto;
    FragmentManager fChildManTopPhoto;


    //Файл для лога
    final static String LOG_TAG="Fragment doctor main";



    //Элементы из левого выдвижного меню
    TextView tv_Surname;
    TextView tv_Name;
    TextView tv_MiddleName;
    Button btn_Settings;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_doctorrelatmain, container, false);

        //Считывание нажатий
        View.OnClickListener oclBnt=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_SettingsDoc:
                        changeFrag.changeFragment();
                        break;
                }
            }
        };

        //Инициализация фрагментов на главном фрагменте
        seeState();
        seeNotes();
        seeList();
        seeInfo();
        seeTop();

        //Инициализация элементов на левом меню
        tv_Surname=v.findViewById(R.id.tv_leftDrawerSurname);
        tv_Name=v.findViewById(R.id.tv_leftDrawerName);
        tv_MiddleName=v.findViewById(R.id.tv_leftDrawerMiddleName);
        btn_Settings=v.findViewById(R.id.btn_SettingsDoc);
        tv_Surname.setText(Profile.getSurname());
        tv_Name.setText(Profile.getName());
        tv_MiddleName.setText(Profile.getMiddlename());
        btn_Settings.setOnClickListener(oclBnt);



        Log.i(LOG_TAG, "--- Left menu elements initialized! ---");
        Log.i(LOG_TAG, "--- Right menu elements initialized! ---");

        return v;
    }


    //Методы для установки отображения некоторых фрагментов
    private void seeList(){
        fNeedyList=new Fragment_NeedyList();
        fChildManList=getChildFragmentManager();
        fChildTranList=fChildManList.beginTransaction();
        fChildTranList.add(R.id.frame_DocRelatUsersList, fNeedyList);
        fChildTranList.commit();
        Log.i(LOG_TAG, "--- Created See_List fragment ---");

    }

    private void seeState(){
        fSeeState=new Fragment_SeeState();
        fChildManState=getChildFragmentManager();
        fChildTranState=fChildManState.beginTransaction();
        fChildTranState.add(R.id.frameDocSeeState, fSeeState);
        fChildTranState.commit();
        Log.i(LOG_TAG, "--- Created See_State fragment ---");
    }

    private void seeNotes(){
        fSeeNotes=new Fragment_SeeNotes();
        fChildManNotes=getChildFragmentManager();
        fChildTranNotes=fChildManNotes.beginTransaction();
        fChildTranNotes.add(R.id.frameDocNotes, fSeeNotes);
        fChildTranNotes.commit();
        Log.i(LOG_TAG, "--- Created See_Notes fragment ---");
    }

    private void seeInfo(){
        fNeedyInfo=new Fragment_NeedyInfo();
        fChildManInfo=getChildFragmentManager();
        fChildTranInfo=fChildManInfo.beginTransaction();
        fChildTranInfo.add(R.id.frame_DocRelatUsersInfo, fNeedyInfo);
        fChildTranInfo.commit();
        Log.i(LOG_TAG, "--- Created See_Info fragment ---");
    }


    private void seeTop(){
        fTopPhoto=new Fragment_TopPhoto();
        fChildManTopPhoto=getChildFragmentManager();
        fChildTranTopPhoto=fChildManTopPhoto.beginTransaction();
        fChildTranTopPhoto.add(R.id.frame_topPhoto, fTopPhoto);
        fChildTranTopPhoto.commit();
        Log.i(LOG_TAG, "--- Created See_TopPhoto fragment ---");
    }

}
