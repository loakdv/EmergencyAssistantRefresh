/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
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
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.R;

/*
Пустой фрагмент для примера создания заявки
 */

public class FragmentLoginCreateRequest extends Fragment {

    Button btnBack, btnConfirm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_create_request, container, false);

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackRequest:
                        getActivity().onBackPressed();
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

        return v;
    }
}
