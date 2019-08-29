/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 4:05 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.customer.settings;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.tooltip.Tooltip;

public class BlockInitials extends Fragment {


    private View v;

    //Информация о самом пользователе
    private TextView
            etSurname,
            etName,
            etMiddleName,
            etInfo,
            etNeedyId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_customer_settings_initials, container, false);
        initializeScreenElements();
        return v;
    }


    private void initializeScreenElements(){
//        etSurname=v.findViewById(R.id.);
//        etName=v.findViewById(R.id.etName);
//        etMiddleName=v.findViewById(R.id.etMiddleName);
//        etInfo = v.findViewById(R.id.etInfo);
        etNeedyId = v.findViewById(R.id.tv_NeedySettingsID);
    }


    //Отдельный метод для быстрого отображения подсказки
    private void showTooltip(View v, int gravity, String text){

        Tooltip tooltip = new Tooltip.Builder(v).
                setText(text).
                setTextColor(Color.WHITE).
                setGravity(gravity).
                setDismissOnClick(true).
                setBackgroundColor(Color.BLUE).
                setCornerRadius(10f).
                show();

    }

    //Метод который выполняет копирование ID пользователя
    private void copyId(){
        /*
        ClipData clipData;

        ClipboardManager clipboardManager;
        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        String id = dataBase.dao_user().getProfile().getId();

        clipData = ClipData.newPlainText("id", id);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(getContext(),"ID был скопирован! ",Toast.LENGTH_SHORT).show();
         */
    }
}
