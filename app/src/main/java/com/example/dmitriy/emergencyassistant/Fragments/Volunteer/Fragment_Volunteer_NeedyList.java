package com.example.dmitriy.emergencyassistant.Fragments.Volunteer;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Volunteer_NeedyList;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Profile;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_String;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Task;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Volunteer;
import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy_Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("ValidFragment")
public class Fragment_Volunteer_NeedyList extends Fragment implements Adapter_Volunteer_NeedyList.CallBackButtons{


    public interface onTaskClick{
        void onTaskClick(Entity_Volunteer_AddedNeedy needy, String date);
    }


    private RecyclerView rvNeedyList;
    private Adapter_Volunteer_NeedyList adapterVolunteerNeedyList;
    private List<Entity_Volunteer_AddedNeedy> needyList=new ArrayList<Entity_Volunteer_AddedNeedy>();

    private DataBase_AppDatabase dataBase;

    private String calendarDate;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private onTaskClick onTaskClick;

    private List<Firebase_Profile> profiles;
    private List<Firebase_Task> tasks;

    private List<String> ids;
    private List<String> times;

    private NeedysThred needysThred;

    private boolean isTasksOpened;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onTaskClick=(onTaskClick)context;
    }

    @SuppressLint("ValidFragment")
    public Fragment_Volunteer_NeedyList(String date){
        this.calendarDate=date;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_needy_list, container, false);

        rvNeedyList=v.findViewById(R.id.rv_Volunteer_Needys);

        isTasksOpened=true;

        initializeFirebase();
        initializeDataBase();

        needysThred=new NeedysThred();
        needysThred.start();


        return v;
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }




    private void initializeList(String date, String needyId){
        if(!(dataBase.dao_volunteer_addedNeedy().getAll()==null)){
            needyList=dataBase.dao_volunteer_addedNeedy().getListBydateID(needyId, date);
            initializeRecycleView();
        }


    }

    private void initializeFirebase(){
        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    private void initializeRecycleView(){
        if(isTasksOpened){
            adapterVolunteerNeedyList=new Adapter_Volunteer_NeedyList(getContext(), needyList,this);
            rvNeedyList.setAdapter(adapterVolunteerNeedyList);
            rvNeedyList.setLayoutManager(new LinearLayoutManager(getContext()));
        }

    }

    @Override
    public void setTask(Entity_Volunteer_AddedNeedy needy) {
        isTasksOpened=false;
        onTaskClick.onTaskClick(needy, calendarDate);
    }





    private class NeedysThred extends Thread{

        private void loadUsers(final String date){
            FirebaseUser user=mAuth.getCurrentUser();

            databaseReference.child("Users").child(user.getUid()).child("Tasks").child(date).child("Profiles").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    ids=new ArrayList<>();

                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                        if(!ids.contains(child.getValue(String.class))){
                            ids.add(child.getValue(String.class));
                        }

                    }

                    loadProfiles(date);
                    for(int i=0; i<ids.size(); i++){
                        Log.e("GET DATA", ids.get(i));
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


        private void loadProfiles(final String date){
            for(int i=0; i<ids.size(); i++){
                databaseReference.child("Users").child(ids.get(i)).child("Profile").
                        addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot child: dataSnapshot.getChildren()) {
                                    profiles=new ArrayList<Firebase_Profile>();
                                    profiles.add(child.getValue(Firebase_Profile.class));

                                    Firebase_Profile profile;
                                    profile=profiles.get(0);


                                    dataBase.dao_volunteer_addedNeedy().insert(new Entity_Volunteer_AddedNeedy(profile.getId(), profile.getName(),
                                            profile.getSurname(), profile.getMiddlename(), dataBase.dao_volunteer().get_Volunteer().getId(), date, false));
                                    Log.e("GET DATA", "USER ADDED");


                                    loadTasks(profile.getId(), date);
                                }

                                onStop();

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
            }
        }


        private void loadTasks(final String id, final String date){
            databaseReference.child("Users").child(id).child("Tasks").child("Task").child(date).child("Times").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    times=new ArrayList<String>();
                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                        times.add(child.getValue(String.class));
                    }


                    for(int i=0; i<times.size(); i++){
                        Log.e("GET DATA", times.get(i)+" "+i);
                    }
                    Log.d("GET DATA", ""+times.size());
                    Log.d("GET DATA", ""+ids.size());

                    initializeTasks(date, id);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }



        private void initializeTasks(final String date, String id){
            for(int i=0; i<times.size(); i++){

                databaseReference.child("Users").child(id).child("Tasks").child("Task").child(date).child(times.get(i)).
                        addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot child: dataSnapshot.getChildren()) {
                                    tasks=new ArrayList<Firebase_Task>();
                                    tasks.add(child.getValue(Firebase_Task.class));

                                    Firebase_Task task;
                                    task=tasks.get(0);

                                    if (!tasks.isEmpty()){
                                        dataBase.dao_volunteer_addedNeedy_task().insert(new Entity_Volunteer_AddedNeedy_Task(task.getTime(), task.getType(),
                                                task.getNeedy_id(), date));
                                        Log.e("GET DATA", "USER ADDED");

                                        initializeList(date, task.getNeedy_id());
                                    }

                                }

                                onStop();

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });

            }

            try{
                wait();
            }
            catch (Exception e){}

        }

        @Override
        public void run() {
            super.run();
            loadUsers(calendarDate);
        }
    }

}