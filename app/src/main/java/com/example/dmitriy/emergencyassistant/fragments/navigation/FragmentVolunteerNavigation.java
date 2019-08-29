/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 1:47 PM
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
import com.example.dmitriy.emergencyassistant.activities.based.ActivityVolunteer;
import com.example.dmitriy.emergencyassistant.interfaces.navigation.InterfaceVolunteerNavigation;

public class FragmentVolunteerNavigation extends Fragment {


    private View v;
    private ToggleButton btnUsers
            ,btnTasks
            ,btnProfile;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_volunteer_navigation, container, false);
        initializeScreenElements();

        SelectorVariant args = (SelectorVariant) getArguments().getSerializable("selected_key");
        selectIndicator(args);
        return v;
    }



    private void initializeScreenElements(){

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnVProfile:
                        ((ActivityVolunteer)getActivity()).setProfile();
                        setIndicatorProfile();
                        break;

                    case R.id.btnVTasksList:
                        ((ActivityVolunteer)getActivity()).setTasks();
                        setIndicatorsTasks();
                        break;

                    case R.id.btnVUsersList:
                        ((ActivityVolunteer)getActivity()).setUsers();
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


    private void selectIndicator(SelectorVariant selectorVariant){
        switch (selectorVariant){
            case TASKS_LIST:
                setIndicatorsTasks();
                break;
            case USERS_LIST:
                setIndicatorUsers();
                break;
            case PROFILE_INFO:
                setIndicatorProfile();
                break;
        }
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
