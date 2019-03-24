package com.example.dmitriy.emergencyassistant;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class Fragment_SeeNeedyInfo extends Fragment {


    private Interface_OnUpdate onUpdate;

    private Button btn_Delete;
    private TextView tv_Surname;
    private TextView tv_Name;
    private TextView tv_Middlename;
    private TextView tv_Info;

    private String name;
    private String surname;
    private String middlename;
    private String info;
    private String id;

    private DataBase_AppDatabase dataBase;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof Interface_OnUpdate) {
            onUpdate = (Interface_OnUpdate) getActivity();
        } else if (getParentFragment() instanceof Interface_OnUpdate) {
            onUpdate = (Interface_OnUpdate) getParentFragment();
        }

    }

    @SuppressLint("ValidFragment")
    public Fragment_SeeNeedyInfo(String name, String surname, String middlename, String info, String id){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.info=info;
        this.id=id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View v=inflater.inflate(R.layout.fragment_seeneedyinfo, container, false);

          initializeDataBase();

          View.OnClickListener oclBtn=new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  switch (v.getId()){
                      case R.id.btn_DeleteNeedy:
                          try {

                              if(dataBase.dao_relative_addedNeedy().getSize()==1){
                                  dataBase.dao_relative_addedNeedy().delete(dataBase.dao_relative_addedNeedy().getById(id));
                                  onUpdate.updateForLast();
                              }
                              else {
                                  dataBase.dao_relative_addedNeedy().delete(dataBase.dao_relative_addedNeedy().getById(id));
                                  onUpdate.updateScreen(dataBase.dao_relative_addedNeedy().getLastNeedy().getId());
                              }

                          }
                          catch (Exception e){
                              Toast.makeText(getContext(), "DELETE USER ERROR", Toast.LENGTH_SHORT).show();
                          }

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

         tv_Surname.setText(surname);
         tv_Name.setText(name);
         tv_Middlename.setText(middlename);
         tv_Info.setText(info);
          return v;
    }


    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }

}
