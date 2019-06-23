package com.example.dmitriy.emergencyassistant.fragments.volunteer;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerTaskList;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentInfoAboutNeedy;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentNotesInfo;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentStateInfo;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOTask;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer.EntityVolunteerAddedNeedyTask;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/*
Фрагмент который отображает список тасков на пользователя у соц. работника
 */

@SuppressLint("ValidFragment")
public class FragmentVolunteerTaskList extends Fragment implements AdapterVolunteerTaskList.CallBackButtons{

    public interface OnTasksClick{
        void goBack();
    }

    private OnTasksClick onTasksClick;

    private AdapterVolunteerTaskList adapterTasks;
    private RecyclerView recyclerViewTask;
    private List<EntityVolunteerAddedNeedyTask> listTasks = new ArrayList<>();

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    private String id;
    private String date;
    private String initials;

    private Button btnBack;

    private DataBaseAppDatabase dataBase;

    private List<POJOTask> POJOTasks;


    //Работа с фрагментом состояния
    private FragmentStateInfo fSeeState;
    private FragmentTransaction fChildTranState;
    private FragmentManager fChildManState;

    //Фрагменты заметок
    private FragmentNotesInfo fSeeNotes;
    private FragmentTransaction fChildTranNotes;
    private FragmentManager fChildManNotes;


    //Фрагмент информации о пользователе
    private FragmentInfoAboutNeedy fNeedyInfo;
    private FragmentTransaction fChildTranInfo;
    private FragmentManager fChildManInfo;





    @SuppressLint("ValidFragment")
    public FragmentVolunteerTaskList(String needyID, String date, String initials){
        this.id = needyID;
        this.date = date;
        this.initials = initials;
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_tasklist, container, false);

        recyclerViewTask=v.findViewById(R.id.rv_VolunteerTasks);

        initializeFirebase();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackTask:
                        onTasksClick.goBack();
                        break;
                }
            }
        };

        initializeDataBase();

        initializeList();


        btnBack=v.findViewById(R.id.btn_BackTask);
        btnBack.setOnClickListener(oclBtn);

        seeInfo();
        seeState();
        seeNotes();


        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onTasksClick=(OnTasksClick)context;
    }




    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }




    private void initializeList(){

        listTasks.add(new EntityVolunteerAddedNeedyTask("time", 2, "id", "date"));
        listTasks.add(new EntityVolunteerAddedNeedyTask("time", 2, "id", "date"));
        listTasks.add(new EntityVolunteerAddedNeedyTask("time", 2, "id", "date"));
        listTasks.add(new EntityVolunteerAddedNeedyTask("time", 2, "id", "date"));
        initializeRecycleView();

        /*
        if(!(dataBase.dao_volunteer_addedNeedy_task().getAll()==null)){
            listTasks=dataBase.dao_volunteer_addedNeedy_task().getByABC(date, id);
            initializeRecycleView();
        }
         */

    }




    private void initializeRecycleView(){
        adapterTasks=new AdapterVolunteerTaskList(getContext(), listTasks, this, initials);
        recyclerViewTask.setAdapter(adapterTasks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTask.setLayoutManager(layoutManager);
    }




    private void initializeFirebase(){
        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }




    @Override
    public void confirmTask(final String needyID, final String date, String time, EntityVolunteerAddedNeedyTask task) {
        FirebaseUser user=mAuth.getCurrentUser();

        dataBase.dao_volunteer_addedNeedy_task().delete(task);

        databaseReference.child("Users").child(needyID).child("Tasks").child("Task").child(date).child(time).removeValue();
        initializeList();

        checkBase(user.getUid(), date);
    }




    private void checkBase(String userId, String date){
        if (adapterTasks.getItemCount()==0){
            databaseReference.child("Users").child(id).child("Tasks").child("Task").child(date).setValue(null);
            if(dataBase.dao_volunteer_addedNeedy_task().getByDate(date)==null){
                databaseReference.child("Users").child(userId).child(date).child("Profiles").setValue(null);
            }
        }
    }

    private void seeState(){
        fSeeState=new FragmentStateInfo(id);
        fChildManState=getChildFragmentManager();
        fChildTranState=fChildManState.beginTransaction();
        fChildTranState.add(R.id.frameCustomerState, fSeeState);
        fChildTranState.commit();
    }

    private void seeNotes(){
        fSeeNotes=new FragmentNotesInfo(id);
        fChildManNotes=getChildFragmentManager();
        fChildTranNotes=fChildManNotes.beginTransaction();
        fChildTranNotes.replace(R.id.frameCustomerNotes , fSeeNotes);
        fChildTranNotes.commit();
    }


    private void seeInfo(){
        fNeedyInfo=new FragmentInfoAboutNeedy();
        fChildManInfo=getChildFragmentManager();
        fChildTranInfo=fChildManInfo.beginTransaction();
        fChildTranInfo.replace(R.id.frameCustomerInfo, fNeedyInfo);
        fChildTranInfo.commit();
    }


}
