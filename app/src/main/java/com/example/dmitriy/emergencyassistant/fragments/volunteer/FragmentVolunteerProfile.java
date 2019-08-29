/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/20/19 11:01 AM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.volunteer;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.dmitriy.emergencyassistant.activities.based.ActivityVolunteerSettings;
import com.tooltip.Tooltip;

public class FragmentVolunteerProfile extends Fragment {


    private View v;

    private Button btnSettings;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_volunteer_profile, container, false);
        initializeScreenElements();
        return v;
    }


    private void initializeScreenElements(){
        btnSettings = v.findViewById(R.id.btnVolunteerProfileSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ActivityVolunteerSettings.class);
                startActivity(i);
            }
        });
    }

    //Метод который копирует Id пользователя
    private void copyId(){
        ClipData clipData;
        ClipboardManager clipboardManager;
        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        String id = "Скопированный ID"; /*dataBase.dao_user().getProfile().getId();*/
        clipData = ClipData.newPlainText("id", id);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(getContext(),"ID был скопирован! ",Toast.LENGTH_SHORT).show();
        //Toast.makeText(getActivity(), "На данный момент функция отключена", Toast.LENGTH_SHORT);
    }


    //Метод который строит подсказку и отображает её
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
}
