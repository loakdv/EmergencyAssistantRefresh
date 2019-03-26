package com.example.dmitriy.emergencyassistant.Fragments;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Profile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Relative;

public class Fragment_TopPhoto extends Fragment {

    private TextView tv_Type;

    private DataBase_AppDatabase dataBase;
    private Entity_Profile profile;
    private Entity_Relative relative;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_topphoto, container, false);
        initializeDataBase();

        tv_Type=v.findViewById(R.id.tv_leftDrawerType);





        if (profile.getType() == 1 && relative.isDoctor()){
            tv_Type.setText("Врач");
        }
        else if(profile.getType() == 1 && !(relative.isDoctor())) {
            tv_Type.setText("Пользователь");
        }






        if(profile.getType() == 2){
            tv_Type.setText("Соц. работник");
        }

        return v;
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();

        profile=dataBase.dao_profile().getProfile();
        if(dataBase.dao_relative().getRelative() != null){
            relative=dataBase.dao_relative().getRelative();
            Log.d("TAG", "RELATIVE: "+relative.getId());
            Log.d("TAG", "RELATIVE: "+relative.getProfile_id());
            Log.d("TAG", "RELATIVE: "+relative.isDoctor());

            Log.d("TAG", "PROFILE: "+profile.getId());
        }

        else {
            Log.d("TAG", "NO USER");
        }




    }
}
