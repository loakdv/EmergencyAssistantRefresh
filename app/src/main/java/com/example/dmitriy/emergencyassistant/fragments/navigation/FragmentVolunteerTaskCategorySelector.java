/*
 *
 *  Created by Dmitry Garmyshev on 19.01.20 9:18
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 19.01.20 9:18
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerTaskView;
import com.example.dmitriy.emergencyassistant.model.service.TaskStatus;

public class FragmentVolunteerTaskCategorySelector extends Fragment {

    private View v;

    private ToggleButton tbntAll
            ,tbtnNew
            ,tbtnProcessing
            ,tbtnPending
            ,tbtnSolved
            ,tbtnClosed;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_volunteer_task_category_selector, container, false);
        initializeScreenElements();
        selectAll();
        return v;
    }

    private void initializeScreenElements(){
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.tbtn_volunteer_tasks_all:
                        selectAll();
                        ((FragmentVolunteerTaskView)getParentFragment()).initializeList(null);
                        break;
                    case R.id.tbtn_volunteer_tasks_new:
                        selectNew();
                        ((FragmentVolunteerTaskView)getParentFragment()).initializeList(TaskStatus.NEW);
                        break;
                    case R.id.tbtn_volunteer_tasks_processing:
                        selectProcessing();
                        ((FragmentVolunteerTaskView)getParentFragment()).initializeList(TaskStatus.PROCESSING);
                        break;
                    case R.id.tbtn_volunteer_pending:
                        selectPending();
                        ((FragmentVolunteerTaskView)getParentFragment()).initializeList(TaskStatus.PENDING);
                        break;
                    case R.id.tbtn_volunteer_tasks_solved:
                        selectSolved();
                        ((FragmentVolunteerTaskView)getParentFragment()).initializeList(TaskStatus.SOLVED);
                        break;
                    case R.id.tbtn_volunteer_tasks_closed:
                        ((FragmentVolunteerTaskView)getParentFragment()).initializeList(TaskStatus.CLOSED);
                        selectClosed();
                        break;
                }
            }
        };

        tbntAll = v.findViewById(R.id.tbtn_volunteer_tasks_all);
        tbntAll.setOnClickListener(oclBtn);

        tbtnNew = v.findViewById(R.id.tbtn_volunteer_tasks_new);
        tbtnNew.setOnClickListener(oclBtn);

        tbtnProcessing = v.findViewById(R.id.tbtn_volunteer_tasks_processing);
        tbtnProcessing.setOnClickListener(oclBtn);

        tbtnPending = v.findViewById(R.id.tbtn_volunteer_pending);
        tbtnPending.setOnClickListener(oclBtn);

        tbtnSolved = v.findViewById(R.id.tbtn_volunteer_tasks_solved);
        tbtnSolved.setOnClickListener(oclBtn);

        tbtnClosed = v.findViewById(R.id.tbtn_volunteer_tasks_closed);
        tbtnClosed.setOnClickListener(oclBtn);

    }


    public void selectAll(){
        tbntAll.setChecked(true);
        tbtnNew.setChecked(false);
        tbtnProcessing.setChecked(false);
        tbtnPending.setChecked(false);
        tbtnSolved.setChecked(false);
        tbtnClosed.setChecked(false);
    }

    private void selectNew(){
        tbntAll.setChecked(false);
        tbtnNew.setChecked(true);
        tbtnProcessing.setChecked(false);
        tbtnPending.setChecked(false);
        tbtnSolved.setChecked(false);
        tbtnClosed.setChecked(false);
    }

    private void selectProcessing(){
        tbntAll.setChecked(false);
        tbtnNew.setChecked(false);
        tbtnProcessing.setChecked(true);
        tbtnPending.setChecked(false);
        tbtnSolved.setChecked(false);
        tbtnClosed.setChecked(false);
    }

    private void selectPending(){
        tbntAll.setChecked(false);
        tbtnNew.setChecked(false);
        tbtnProcessing.setChecked(false);
        tbtnPending.setChecked(true);
        tbtnSolved.setChecked(false);
        tbtnClosed.setChecked(false);
    }

    private void selectSolved(){
        tbntAll.setChecked(false);
        tbtnNew.setChecked(false);
        tbtnProcessing.setChecked(false);
        tbtnPending.setChecked(false);
        tbtnSolved.setChecked(true);
        tbtnClosed.setChecked(false);
    }

    private void selectClosed(){
        tbntAll.setChecked(false);
        tbtnNew.setChecked(false);
        tbtnProcessing.setChecked(false);
        tbtnPending.setChecked(false);
        tbtnSolved.setChecked(false);
        tbtnClosed.setChecked(true);
    }
}
