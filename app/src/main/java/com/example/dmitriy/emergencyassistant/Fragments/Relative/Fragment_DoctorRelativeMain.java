package com.example.dmitriy.emergencyassistant.Fragments.Relative;

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

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds.Activity_Dialog_AddNewUser;
import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Relative_AddedNeedy;
import com.example.dmitriy.emergencyassistant.Fragments.InfoBlocks.Fragment_SeeNeedyInfo;
import com.example.dmitriy.emergencyassistant.Fragments.InfoBlocks.Fragment_SeeNotes;
import com.example.dmitriy.emergencyassistant.Fragments.InfoBlocks.Fragment_SeeState;
import com.example.dmitriy.emergencyassistant.Fragments.InfoBlocks.Fragment_TopPhoto;
import com.example.dmitriy.emergencyassistant.Interfaces.Interface_OnUpdate;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.Entity_Relative_AddedNeedy;

import java.util.ArrayList;
import java.util.List;

public class Fragment_DoctorRelativeMain<onStart> extends Fragment implements Adapter_Relative_AddedNeedy.CallBackButtons, Interface_OnUpdate {



    private boolean isPaused=false;

    private Entity_Relative_AddedNeedy lastNeedy;

    private List<Entity_Relative_AddedNeedy> needyfordoc=new ArrayList<Entity_Relative_AddedNeedy>();
    private Adapter_Relative_AddedNeedy a_needy_fordoc;
    private RecyclerView r_needy;

    /*
    Этот интерфейс имплементируется активностью доктора
    Он необходим для смены рабочего фрагмента
     */
    public interface onChangeDocFrag{
       void changeFragment();
    }

    //Объект интерфеся для смены рабочего фрагмента
    private onChangeDocFrag changeFrag;

    private Adapter_Relative_AddedNeedy adapter;

    //Инициализируем объект интерфейчас при присоединении
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeFrag=(onChangeDocFrag)context;
    }



    //Работа с фрагментом состояния
    private Fragment_SeeState fSeeState;
    private FragmentTransaction fChildTranState;
    private FragmentManager fChildManState;



    //Фрагменты заметок
    private Fragment_SeeNotes fSeeNotes;
    private FragmentTransaction fChildTranNotes;
    private FragmentManager fChildManNotes;


    //Фрагмент информации о пользователе
    private Fragment_SeeNeedyInfo fNeedyInfo;
    private FragmentTransaction fChildTranInfo;
    private FragmentManager fChildManInfo;


    //Фрагмент с фото в левом меню
    private Fragment_TopPhoto fTopPhoto;
    private FragmentTransaction fChildTranTopPhoto;
    private FragmentManager fChildManTopPhoto;

    //Фрагмент при невыбранном пользователе
    private Fragment_No_Selected_Needy fNone;
    private FragmentTransaction fChildTranNone;
    private FragmentManager fChildManNone;


    //Файл для лога
    final static String LOG_TAG="Fragment doctor main";



    //Элементы из левого выдвижного меню
    private TextView tv_Surname;
    private TextView tv_Name;
    private TextView tv_MiddleName;
    private TextView tv_id;
    private Button btn_Settings;
    private Button btn_Call;
    private Button btn_new;

    private DataBase_AppDatabase dataBase;

    private Entity_Profile profile;


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




    private void seeState(String id){
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




    private void seeNotes(String id){
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




    private void seeInfo(String id){
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




    private void updateFragments(String last_id){
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
        String id=dataBase.dao_profile().getProfile().getId();
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
    public void updateScreen(String id) {
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
