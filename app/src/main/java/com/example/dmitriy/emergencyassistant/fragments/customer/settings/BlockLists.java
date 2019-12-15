/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 4:01 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.customer.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.activities.dialogs.lists.ActivityDialogNumbers;

public class BlockLists extends Fragment {


    private View v;

    private Button btnSocial
            ,btnNumbers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_customer_settings_lists_switch, container, false);
        initializeScreenElements();
        return v;
    }

    private void initializeScreenElements(){
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_SocialHelp:
                        startSocial();
                        break;
                    case R.id.btn_Numbers:
                        startNumbers();
                        break;
                }
            }
        };

        btnSocial = v.findViewById(R.id.btn_SocialHelp);
        btnSocial.setOnClickListener(oclBtn);

        btnNumbers = v.findViewById(R.id.btn_Numbers);
        btnNumbers.setOnClickListener(oclBtn);

    }

    //Открываем окно с номерами телефонов
    private void startNumbers(){
        Intent numbers=new Intent(getContext(), ActivityDialogNumbers.class);
        startActivity(numbers);
    }

    //Открываем окно с информацией о соц. обслуживании
    private void startSocial(){
        /*
        if(dataBase.dao_needy_volunteer().getVolunteer() != null){
            Intent i = new Intent(getContext(), ActivityDialogSocialInfo.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(getContext(), ActivityDialogAddVolunteer.class);
            startActivity(i);
        }
         */
    }
}
