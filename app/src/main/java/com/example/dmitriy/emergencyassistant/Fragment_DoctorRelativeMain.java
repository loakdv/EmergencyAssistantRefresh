package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_DoctorRelativeMain<onStart> extends Fragment implements Adapter_Relative_AddedNeedy.CallBackButtons, Interface_OnUpdate{



    boolean isPaused=false;

    Entity_Relative_AddedNeedy lastNeedy;

    List<Entity_Relative_AddedNeedy> needyfordoc=new ArrayList<Entity_Relative_AddedNeedy>();
    Adapter_Relative_AddedNeedy a_needy_fordoc;
    RecyclerView r_needy;

    /*
    Этот интерфейс имплементируется активностью доктора
    Он необходим для смены рабочего фрагмента
     */
    public interface onChangeDocFrag{
       void changeFragment();
    }

    //Объект интерфеся для смены рабочего фрагмента
    onChangeDocFrag changeFrag;

    Adapter_Relative_AddedNeedy adapter;

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


    //Фрагмент информации о пользователе
    Fragment_SeeNeedyInfo fNeedyInfo;
    FragmentTransaction fChildTranInfo;
    FragmentManager fChildManInfo;


    //Фрагмент с фото в левом меню
    Fragment_TopPhoto fTopPhoto;
    FragmentTransaction fChildTranTopPhoto;
    FragmentManager fChildManTopPhoto;

    //Фрагмент при невыбранном пользователе
    Fragment_No_Selected_Needy fNone;
    FragmentTransaction fChildTranNone;
    FragmentManager fChildManNone;


    //Файл для лога
    final static String LOG_TAG="Fragment doctor main";



    //Элементы из левого выдвижного меню
    TextView tv_Surname;
    TextView tv_Name;
    TextView tv_MiddleName;
    TextView tv_id;
    Button btn_Settings;
    Button btn_Call;
    Button btn_new;

    DataBase_AppDatabase dataBase;

    Entity_Profile profile;


    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

       View v=inflater.inflate(R.layout.fragment_doctorrelatmain,
               container, false);

       initializeDataBase();

        //Считывание нажатий
        View.OnClickListener oclBnt=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_SettingsDoc:
                        changeFrag.changeFragment();
                        isPaused=true;
                        break;
                    case R.id.btn_RelativeCall:
                        break;
                    case R.id.btn_AddNewNeedy2:
                        Intent newNeedy=new Intent(getContext(), Activity_Dialog_AddNewUser.class);
                        newNeedy.putExtra("type", 1);
                        startActivity(newNeedy);
                        break;
                }
            }
        };


        //Инициализация элементов на левом меню
        tv_Surname=v.findViewById(R.id.tv_leftDrawerSurname);
        tv_Name=v.findViewById(R.id.tv_leftDrawerName);
        tv_MiddleName=v.findViewById(R.id.tv_leftDrawerMiddleName);
        tv_id=v.findViewById(R.id.tv_DocRelatId);
        btn_Settings=v.findViewById(R.id.btn_SettingsDoc);
        tv_Surname.setText(profile.getSurname());
        tv_Name.setText(profile.getName());
        tv_MiddleName.setText(profile.getMiddlename());
        btn_Settings.setOnClickListener(oclBnt);
        btn_Call=v.findViewById(R.id.btn_RelativeCall);
        btn_Call.setOnClickListener(oclBnt);
        btn_Call.setVisibility(View.GONE);

        r_needy=v.findViewById(R.id.rv_NeedyList);
        btn_new=v.findViewById(R.id.btn_AddNewNeedy2);
        btn_new.setOnClickListener(oclBnt);


        initializeList();
        initializeRecycleView();
        seeTop();
        setId();
        afterPause();

        return v;
    }




    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_profile().getProfile();
    }




    @Override
    public void select(Entity_Relative_AddedNeedy needy) {
        seeInfo(needy.getId());
        seeState(needy.getId());
        seeNotes(needy.getId());
        lastNeedy=needy;
        btn_Call.setVisibility(View.VISIBLE);
    }




    private void initializeList(){
        if(!(dataBase.dao_relative_addedNeedy().getAll()==null)){
            needyfordoc=dataBase.dao_relative_addedNeedy().getAll();
        }
    }




    private void initializeRecycleView(){
        a_needy_fordoc=new Adapter_Relative_AddedNeedy(getActivity(), needyfordoc,this);
        r_needy.setAdapter(a_needy_fordoc);
        r_needy.setLayoutManager(new LinearLayoutManager(getContext()));
    }




    private void seeState(long id){
        fSeeState=new Fragment_SeeState(id);
        fChildManState=getChildFragmentManager();
        fChildTranState=fChildManState.beginTransaction();
        fChildTranState.add(R.id.frameDocSeeState, fSeeState);
        fChildTranState.commit();
        Log.i(LOG_TAG, "--- Created See_State fragment ---");
    }

    private void removeState(){
        fChildManState=getChildFragmentManager();
        fChildTranState=fChildManState.beginTransaction();
        fChildTranState.remove(fSeeState);
        fChildTranState.commit();
    }

    private void seeNotes(long id){
        fSeeNotes=new Fragment_SeeNotes(id);
        fChildManNotes=getChildFragmentManager();
        fChildTranNotes=fChildManNotes.beginTransaction();
        fChildTranNotes.replace(R.id.frameDocNotes, fSeeNotes);
        fChildTranNotes.commit();
        Log.i(LOG_TAG, "--- Created See_Notes fragment ---");
    }

    private void removeNotes(){
        fChildManNotes=getChildFragmentManager();
        fChildTranNotes=fChildManNotes.beginTransaction();
        fChildTranNotes.remove(fSeeNotes);
        fChildTranNotes.commit();
    }

    private void seeInfo(long id){
        Entity_Relative_AddedNeedy needy=dataBase.dao_relative_addedNeedy().getById(id);
        String name=needy.getName();
        String surname=needy.getSurname();
        String middlename=needy.getMiddlename();
        String info=needy.getInfo();
        fNeedyInfo=new Fragment_SeeNeedyInfo(name, surname, middlename, info, id);
        fChildManInfo=getChildFragmentManager();
        fChildTranInfo=fChildManInfo.beginTransaction();
        fChildTranInfo.replace(R.id.frame_DocRelatUsersInfo, fNeedyInfo);
        fChildTranInfo.commit();
        Log.i(LOG_TAG, "--- Created See_Info fragment ---");
    }

    private void removeInfo(){
        fChildManInfo=getChildFragmentManager();
        fChildTranInfo=fChildManInfo.beginTransaction();
        fChildTranInfo.remove(fNeedyInfo);
        fChildTranInfo.commit();
    }


    private void seeTop(){
        fTopPhoto=new Fragment_TopPhoto();
        fChildManTopPhoto=getChildFragmentManager();
        fChildTranTopPhoto=fChildManTopPhoto.beginTransaction();
        fChildTranTopPhoto.add(R.id.frame_topPhoto, fTopPhoto);
        fChildTranTopPhoto.commit();
        Log.i(LOG_TAG, "--- Created See_TopPhoto fragment ---");
    }

    private void updateFragments(long last_id){
        seeState(last_id);
        seeNotes(last_id);
        seeInfo(last_id);
        initializeList();
        initializeRecycleView();
        btn_Call.setVisibility(View.VISIBLE);
    }

    private void setNone(){
        fNone=new Fragment_No_Selected_Needy();
        fChildManNone=getChildFragmentManager();
        fChildTranNone=fChildManNone.beginTransaction();
        fChildTranNone.add(R.id.frameDocSeeState, fNone);
        fChildTranNone.commit();
    }

    private void replaceNone(){
        fNone=new Fragment_No_Selected_Needy();
        fChildManNone=getChildFragmentManager();
        fChildTranNone=fChildManNone.beginTransaction();
        fChildTranNone.replace(R.id.frameDocSeeState, fNone);
        fChildTranNone.commit();
    }

    private void afterPause(){
        if(lastNeedy==null){
            setNone();
        }
        else {
            if(isPaused){
                select(lastNeedy);
            }
            else {
                removeNotes();
                removeState();
                setNone();
            }
        }

    }




    private void setId(){
        String id=Long.toString(dataBase.dao_profile().getProfile().getId());
        tv_id.setText("Ваш ID: "+id);
    }




    @Override
    public void onResume() {
        super.onResume();
        onStart();
    }





    @Override
    public void onStart() {
        super.onStart();
        initializeList();
        initializeRecycleView();
    }





    @Override
    public void updateScreen(long id) {
        updateFragments(id);
    }




    @Override
    public void updateForLast() {
        initializeList();
        initializeRecycleView();
        replaceNone();
        removeInfo();
        removeNotes();
        btn_Call.setVisibility(View.GONE);
    }




}
