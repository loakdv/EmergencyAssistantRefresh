package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Progressbar_State extends Fragment {
    /*
    Фрагмент для того маленького прогресс-бара с состоянием
    Уже бесполезен
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_progressbar_state, container, false);
        return v;
    }
}
