package com.example.dmitriy.emergencyassistant.fragments.customer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmitriy.emergencyassistant.R;

/*
    Фрагмент с простой надписью о том что доступ недоступев в этот раздел
     */

public class FragmentCustomerSettingsNone extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_needysettings_none, container, false);
        return v;
    }
}