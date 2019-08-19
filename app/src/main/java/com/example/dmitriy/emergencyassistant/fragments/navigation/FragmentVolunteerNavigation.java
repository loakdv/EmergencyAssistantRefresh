/*
 *
 *  Created by Dmitry Garmyshev on 8/19/19 5:18 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/19/19 4:42 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.navigation.InterfaceVolunteerNavigation;

public class FragmentVolunteerNavigation extends Fragment {


    private View v;
    private ToggleButton btnUsers
            ,btnTasks
            ,btnProfile;

    private InterfaceVolunteerNavigation interfaceVolunteerNavigation;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interfaceVolunteerNavigation = (InterfaceVolunteerNavigation) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_volunteer_navigation, container, false);

        boolean ard = getArguments().getBoolean("list_active", false);

        initializeScreenElements();

        if(ard){setIndicatorUsers();}
        return v;
    }



    private void initializeScreenElements(){

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnVProfile:
                        interfaceVolunteerNavigation.setProfile();
                        setIndicatorProfile();
                        break;

                    case R.id.btnVTasksList:
                        interfaceVolunteerNavigation.setTasks();
                        setIndicatorsTasks();
                        break;

                    case R.id.btnVUsersList:
                        interfaceVolunteerNavigation.setUsers();
                        setIndicatorUsers();
                        break;
                }
            }
        };


        btnProfile = v.findViewById(R.id.btnVProfile);
        btnProfile.setOnClickListener(oclBtn);

        btnTasks = v.findViewById(R.id.btnVTasksList);
        btnTasks.setOnClickListener(oclBtn);

        btnUsers = v.findViewById(R.id.btnVUsersList);
        btnUsers.setOnClickListener(oclBtn);
    }


    public void setIndicatorUsers(){
        btnUsers.setChecked(true);
        unsetTasks();
        unsetProfile();
    }

    public void setIndicatorsTasks(){
        btnTasks.setChecked(true);
        unsetUsers();
        unsetProfile();
    }

    public void setIndicatorProfile(){
        btnProfile.setChecked(true);
        unsetTasks();
        unsetUsers();
    }


    private void unsetUsers(){
        btnUsers.setChecked(false);
    }

    private void unsetTasks(){
        btnTasks.setChecked(false);
    }

    private void unsetProfile(){
        btnProfile.setChecked(false);
    }

}
