package com.example.dmitriy.emergencyassistant.Fragments.InfoBlocks;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Profile;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_State;
import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

@SuppressLint("ValidFragment")
public class Fragment_SeeState extends Fragment {

    //Фрагмент с блоками состояния(прогрессбарами)

    private ProgressBar pb_9;
    private ProgressBar pb_12;
    private ProgressBar pb_15;
    private ProgressBar pb_18;
    private ProgressBar pb_21;

    private List<Firebase_State> statesList;

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    String selectedId;

    DataBase_AppDatabase dataBase;

    @SuppressLint("ValidFragment")
    public Fragment_SeeState(String id){
        this.selectedId=id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_seestate, container, false);

        initializeDataBase();


        databaseReference= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();

        pb_9=v.findViewById(R.id.progressBar_9);
        pb_12=v.findViewById(R.id.progressBar12);
        pb_15=v.findViewById(R.id.progressBar15);
        pb_18=v.findViewById(R.id.progressBar18);
        pb_21=v.findViewById(R.id.progressBar21);

        setProgress();
        return v;
    }


    public void setProgress(){



        databaseReference.child("Users").child(selectedId).child("State").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                statesList = new ArrayList<>();

                 /*
                Получение состояний мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                  statesList.add(child.getValue(Firebase_State.class));


                }


                int states[]=new int[5];

                for(int i=0; i<states.length; i++){
                    states[i]=0;
                }

                for(int i=0; i<statesList.size(); i++){
                    states[i]=statesList.get(i).getPercent();
                }

                initializeState(states);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializeState(int[] states){
        pb_9.setProgress(states[0]);
        pb_12.setProgress(states[1]);
        pb_15.setProgress(states[2]);
        pb_18.setProgress(states[3]);
        pb_21.setProgress(states[4]);
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }


}
