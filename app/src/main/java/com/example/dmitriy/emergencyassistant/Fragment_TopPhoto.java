package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_TopPhoto extends Fragment {

      TextView tv_Type;

    DataBase_AppDatabase dataBase;
    Entity_Profile profile;
    Entity_Relative relative;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_topphoto, container, false);
        initializeDataBase();

        tv_Type=v.findViewById(R.id.tv_leftDrawerType);


        if(profile.getType()==1&&relative.isDoctor()){
            tv_Type.setText("Врач");
        }
        else if(profile.getType()==1&&!(relative.isDoctor())) {
            tv_Type.setText("Пользователь");
        }

        if(profile.getType()==3){
            tv_Type.setText("Соц. работник");
        }

        return v;
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
        profile=dataBase.dao_profile().getProfile();
        relative=dataBase.dao_relative().getRelative();
    }
}
