package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment_Volunteer_Main extends Fragment {


    /*
   Этот интерфейс имплементируется активностью доктора
   Он необходим для смены рабочего фрагмента
    */
    public interface onChangeVolunFrag{
        void setMain();
        void setSettings();
        void setMap();
    }


    onChangeVolunFrag changeVolun;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeVolun=(onChangeVolunFrag)context;
    }

    //Фрагменты заметок
    Fragment_SeeNotes fSeeNotes;
    FragmentTransaction fChildTranNotes;
    FragmentManager fChildManNotes;

    //Фрагмент с фото в левом меню
    Fragment_TopPhoto fTopPhoto;
    FragmentTransaction fChildTranTopPhoto;
    FragmentManager fChildManTopPhoto;

    TextView tv_Surname;
    TextView tv_Name;
    TextView tv_MiddleName;
    Button btn_Settings;
    Button btn_Map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_main, container, false);
        seeNotes();
        seeTop();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_VolunteerSettings:
                        changeVolun.setSettings();
                        break;
                    case R.id.btn_VolunteerMap:
                        changeVolun.setMap();
                        break;
                }
            }
        };

        btn_Settings=v.findViewById(R.id.btn_VolunteerSettings);
        btn_Settings.setOnClickListener(oclBtn);

        btn_Map=v.findViewById(R.id.btn_VolunteerMap);
        btn_Map.setOnClickListener(oclBtn);

        tv_Surname=v.findViewById(R.id.tv_VolunteerSurname);
        tv_Surname.setText(Entity_Profile.getSurname());
        tv_Name=v.findViewById(R.id.tv_VolunteerName);
        tv_Name.setText(Entity_Profile.getName());
        tv_MiddleName=v.findViewById(R.id.tv_VolunteerMiddleName);
        tv_MiddleName.setText(Entity_Profile.getMiddlename());
        return v;
    }


    private void seeNotes(){
        fSeeNotes=new Fragment_SeeNotes();
        fChildManNotes=getChildFragmentManager();
        fChildTranNotes=fChildManNotes.beginTransaction();
        fChildTranNotes.add(R.id.frame_VolunteerNotes, fSeeNotes);
        fChildTranNotes.commit();
        Log.i("LOG_TAG", "--- Created See_Notes fragment ---");
    }

    private void seeTop(){
        fTopPhoto=new Fragment_TopPhoto();
        fChildManTopPhoto=getChildFragmentManager();
        fChildTranTopPhoto=fChildManTopPhoto.beginTransaction();
        fChildTranTopPhoto.add(R.id.frame_VolunteerPhoto, fTopPhoto);
        fChildTranTopPhoto.commit();
        Log.i("LOG_TAG", "--- Created See_TopPhoto fragment ---");
    }

}
