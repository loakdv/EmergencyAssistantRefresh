package com.example.dmitriy.emergencyassistant.Fragments.Relative;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;
import com.tooltip.Tooltip;


/*
Фрагмент который необходим для того что-бы показать что пользователь не выбран
 */

public class Fragment_No_Selected_Needy extends Fragment {

    Button btnHelp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_no_selected_needy, container, false);


        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_fragment_noselneedy_help:
                        showTooltip(v, Gravity.BOTTOM, "Для доступа к основному меню используйте левое " +
                                "боковое меню." +
                                "\n \n" +
                                "Для подключения или выбора пользователей используйте правое боковое меню " +
                                "(Там же вы сможете увидеть и информацию о пользователе)." +
                                "\n \n" +
                                "(Нажмите на сообщение чтобы закрыть его)");
                        break;
                }
            }
        };


        btnHelp = v.findViewById(R.id.btn_fragment_noselneedy_help);
        btnHelp.setOnClickListener(oclBtn);
        return v;
    }


    private void showTooltip(View v, int gravity, String text){

        Tooltip tooltip = new Tooltip.Builder(v).
                setText(text).
                setTextColor(Color.WHITE).
                setGravity(gravity).
                setDismissOnClick(true).
                setTextSize(15f).
                setBackgroundColor(Color.BLUE).
                setCornerRadius(10f).
                show();

    }
}
