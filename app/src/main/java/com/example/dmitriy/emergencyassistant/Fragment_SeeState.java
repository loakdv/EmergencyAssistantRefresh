package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class Fragment_SeeState extends Fragment {

    //Фрагмент с блоками состояния(прогрессбарами)

    ProgressBar pb_9;
    ProgressBar pb_12;
    ProgressBar pb_15;
    ProgressBar pb_18;
    ProgressBar pb_21;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_seestate, container, false);

        pb_9=v.findViewById(R.id.progressBar_9);
        pb_12=v.findViewById(R.id.progressBar12);
        pb_15=v.findViewById(R.id.progressBar15);
        pb_18=v.findViewById(R.id.progressBar18);
        pb_21=v.findViewById(R.id.progressBar21);
        setProgress();
        return v;
    }

    public void setProgress(/*int s9, int s12, int s15, int s18, int s21*/){
        pb_9.setProgress((int) (Math.random() * 101));
        pb_12.setProgress((int) (Math.random() * 101));
        pb_15.setProgress((int) (Math.random() * 101));
        pb_18.setProgress((int) (Math.random() * 101));
        pb_21.setProgress((int) (Math.random() * 101));
    }
}
