package com.example.dmitriy.emergencyassistant;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_topphoto, container, false);

        tv_Type=v.findViewById(R.id.tv_leftDrawerType);

        if(Entity_Profile.isDoctor()){
            tv_Type.setText("Врач");
        }
        else {
            tv_Type.setText("Пользователь");
        }

        if(Entity_Profile.getType()==3){
            tv_Type.setText("Соц. работник");
        }
        return v;
    }
}
