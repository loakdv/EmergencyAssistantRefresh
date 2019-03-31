package com.example.dmitriy.emergencyassistant.Fragments.Volunteer;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
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
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Volunteer;
import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Fragment_Volunteer_NeedyList extends Fragment implements Adapter_Volunteer_NeedyList.CallBackButtons{


    public interface onTaskClick{
        void onTaskClick(Entity_Volunteer_AddedNeedy needy);
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

        initializeFirebase();
        initializeDataBase();


        initializeList();
        initializeRecycleView();

        loadUsers(calendarDate);

        initializeRecycleView();
        return v;
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }



    private void initializeList(){
        if(!(dataBase.dao_volunteer_addedNeedy().getAll()==null)){
            needyList=dataBase.dao_volunteer_addedNeedy().getAll();
        }

    }

    private void initializeFirebase(){
        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    private void initializeRecycleView(){
        adapterVolunteerNeedyList=new Adapter_Volunteer_NeedyList(getContext(), needyList,this);
        rvNeedyList.setAdapter(adapterVolunteerNeedyList);
        rvNeedyList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setTask(Entity_Volunteer_AddedNeedy needy) {
        onTaskClick.onTaskClick(needy);
    }


    private void loadUsers(String date){
        FirebaseUser user=mAuth.getCurrentUser();



        databaseReference.child("Users").child(user.getUid()).child("Tasks").child(date).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    profiles=new ArrayList<Firebase_Profile>();
                    profiles.add(child.getValue(Firebase_Profile.class));



                    Firebase_Profile profile;
                    profile=profiles.get(0);
                    Log.d("DOWNLOAD", "GET PROFILE");


                    dataBase.dao_volunteer_addedNeedy().insert(new Entity_Volunteer_AddedNeedy(profile.getId(), profile.getName(),
                            profile.getSurname(), profile.getMiddlename(), dataBase.dao_volunteer().get_Volunteer().getId()));

                    Log.d("DOWNLOAD", "ADD TO LOCAL");


                    initializeList();
                    initializeRecycleView();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
