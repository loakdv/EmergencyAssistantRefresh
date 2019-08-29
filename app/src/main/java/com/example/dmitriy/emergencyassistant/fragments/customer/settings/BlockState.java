/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 4:10 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.customer.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.activities.based.ActivityCustomerSettings;

public class BlockState extends Fragment {

    private View v;

    private ToggleButton btnSelect;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_customer_settings_state_selector, container, false);
        initializeScreenElements();
        return v;
    }


    private void initializeScreenElements(){
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnSelect.isChecked()){
                    //stop service
                }
                else {
                    //start service
//                    ((ActivityCustomerSettings)getActivity()).startService();
                }

            }
        };

        btnSelect = v.findViewById(R.id.btnStateToggle);
        btnSelect.setOnClickListener(oclBtn);

    }



    private void setChecked(){
        btnSelect.setChecked(true);
    }

    private void setUnchecked(){
        btnSelect.setChecked(false);
    }
}
