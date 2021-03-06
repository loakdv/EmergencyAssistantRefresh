/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 2:23 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.activities.based.ActivityLogin;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;

/*
Пустой фрагмент для примера создания заявки
 */

public class FragmentLoginCreateRequest extends Fragment implements InterfaceInitialize {

    //Элементы экрана
    private Button
            btnBack,
            btnConfirm;
    private LinearLayout lnLoading;
    private View v;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_login_create_request, container, false);
        initializeScreenElements();
        return v;
    }




    @Override
    public void initializeScreenElements() {
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackRequest:
                        ((ActivityLogin)getActivity()).setFirst();
                        break;
                    case R.id.btn_CreateRequest:
                        Toast.makeText(getContext(), "Запрос был отправлен в организацию!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        
        btnBack = v.findViewById(R.id.btn_BackRequest);
        btnBack.setOnClickListener(oclBtn);

        btnConfirm = v.findViewById(R.id.btn_CreateRequest);
        btnConfirm.setOnClickListener(oclBtn);

        lnLoading = v.findViewById(R.id.lnCRL);
    }
}
