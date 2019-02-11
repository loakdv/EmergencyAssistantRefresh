package com.example.dmitriy.emergencyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class Fragment_SeeNeedyList extends Fragment {

    Button btn_new;

    static int selectedPosition;

    static ArrayList<Added_Needy> needyfordoc=new ArrayList<Added_Needy>();
    Adapter_Relative_AddedNeedy a_needy_fordoc;
    static RecyclerView r_needy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_seeneedylist, container, false);
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  switch (v.getId()){
                      case R.id.btn_AddNewNeedy:
                          Intent newNeedy=new Intent(getContext(), Activity_Dialog_AddNewUser.class);
                          if(Entity_Profile.isDoctor()){
                              newNeedy.putExtra("doctor", true);
                          }

                          startActivity(newNeedy);
                          break;
                  }
            }
        };

        r_needy=v.findViewById(R.id.rv_NeedyList);
        btn_new=v.findViewById(R.id.btn_AddNewNeedy);
        btn_new.setOnClickListener(oclBtn);

        a_needy_fordoc=new Adapter_Relative_AddedNeedy(getContext(), needyfordoc);
        r_needy.setAdapter(a_needy_fordoc);
        r_needy.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    public static void addNeedy(String id){
        needyfordoc.add(new Added_Needy( id));
        r_needy.getAdapter().notifyDataSetChanged();
    }

    public static void deleteNeedy(int position){
        needyfordoc.remove(position);
        r_needy.getAdapter().notifyDataSetChanged();
    }
}
