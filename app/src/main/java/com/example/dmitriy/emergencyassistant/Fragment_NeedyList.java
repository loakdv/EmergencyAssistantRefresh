package com.example.dmitriy.emergencyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_NeedyList extends Fragment {

    Button btn_new;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_needylist, container, false);
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  switch (v.getId()){
                      case R.id.btn_AddNewNeedy:
                          Intent newNeedy=new Intent(getContext(), Activity_Dialog_AddNewUser.class);
                          startActivity(newNeedy);
                          break;
                  }
            }
        };
        btn_new=v.findViewById(R.id.btn_AddNewNeedy);
        btn_new.setOnClickListener(oclBtn);
        return v;
    }
}
