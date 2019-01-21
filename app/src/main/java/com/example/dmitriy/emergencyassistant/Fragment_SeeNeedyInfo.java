package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment_SeeNeedyInfo extends Fragment {

    static int selectedNeedy;
    static String selectedName;
    static String selectedSurname;
    static String selectedMiddleName;
    static String selectedInfo;

    Button btn_Delete;
    static TextView tv_Surname;
    static TextView tv_Name;
    static TextView tv_Middlename;
    static TextView tv_Info;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View v=inflater.inflate(R.layout.fragment_seeneedyinfo, container, false);

          View.OnClickListener oclBtn=new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  switch (v.getId()){
                      case R.id.btn_DeleteNeedy:
                          try{
                              Fragment_SeeNeedyList.deleteNeedy(selectedNeedy);
                          }
                          catch (Exception e){}


                          break;
                  }
              }
          };

          tv_Surname=v.findViewById(R.id.tv_NeedyInfoSurname);
          tv_Name=v.findViewById(R.id.tv_NeedyInfoName);
          tv_Middlename=v.findViewById(R.id.tv_NeedyInfoMiddlename);
          tv_Info=v.findViewById(R.id.tv_NeedyInfoInfo);


          btn_Delete=v.findViewById(R.id.btn_DeleteNeedy);
          btn_Delete.setOnClickListener(oclBtn);
          return v;
    }

    public static int getSelectedNeedy() {
        return selectedNeedy;
    }

    public static void setSelectedNeedy(int selectedNeedy) {
        Fragment_SeeNeedyInfo.selectedNeedy = selectedNeedy;
    }

    public static void setInfo(String name, String surname, String middlename, String info){
        tv_Surname.setText(surname);
        tv_Name.setText(name);
        tv_Middlename.setText(middlename);
        tv_Info.setText(info);
    }

}
