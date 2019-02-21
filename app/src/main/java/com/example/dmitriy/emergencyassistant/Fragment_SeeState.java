package com.example.dmitriy.emergencyassistant;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

@SuppressLint("ValidFragment")
public class Fragment_SeeState extends Fragment {

    //Фрагмент с блоками состояния(прогрессбарами)

    ProgressBar pb_9;
    ProgressBar pb_12;
    ProgressBar pb_15;
    ProgressBar pb_18;
    ProgressBar pb_21;

    int s9;
    int s12;
    int s15;
    int s18;
    int s21;

    long selectedId;

    DataBase_AppDatabase dataBase;

    @SuppressLint("ValidFragment")
    public Fragment_SeeState(long id){
        this.selectedId=id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_seestate, container, false);

        initializeDataBase();
        initializeState();

        pb_9=v.findViewById(R.id.progressBar_9);
        pb_12=v.findViewById(R.id.progressBar12);
        pb_15=v.findViewById(R.id.progressBar15);
        pb_18=v.findViewById(R.id.progressBar18);
        pb_21=v.findViewById(R.id.progressBar21);
        setProgress();
        return v;
    }

    public void setProgress(){
        int s9rand= 0 + (int) (Math.random() * 100);
        int s12rand= 0 + (int) (Math.random() * 100);
        int s15rand= 0 + (int) (Math.random() * 100);
        int s18rand= 0 + (int) (Math.random() * 100);
        int s21rand= 0 + (int) (Math.random() * 100);
        pb_9.setProgress(s9rand);
        pb_12.setProgress(s12rand);
        pb_15.setProgress(s15rand);
        pb_18.setProgress(s18rand);
        pb_21.setProgress(s21rand);
    }

    private void initializeState(){
        s9=dataBase.dao_relative_addedNeedy_state().getS9(selectedId);
        s12=dataBase.dao_relative_addedNeedy_state().getS12(selectedId);
        s15=dataBase.dao_relative_addedNeedy_state().getS15(selectedId);
        s18=dataBase.dao_relative_addedNeedy_state().getS18(selectedId);
        s21=dataBase.dao_relative_addedNeedy_state().getS21(selectedId);
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }


}
