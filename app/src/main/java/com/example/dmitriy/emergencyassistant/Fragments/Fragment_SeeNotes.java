package com.example.dmitriy.emergencyassistant.Fragments;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
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

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Activity_Dialog_AddNote;
import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Relative_AddedNeedy_Note;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Relative_AddedNeedy_Note;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Fragment_SeeNotes extends Fragment implements Adapter_Relative_AddedNeedy_Note.CallBackButtons {

    //нопка для длбавления фрагментов
    private Button btn_AddNote;

    //Динамический массив для хранения заметок
    private List<Entity_Relative_AddedNeedy_Note> notes=new ArrayList<Entity_Relative_AddedNeedy_Note>();
    //Экзеипляр адаптера
    private Adapter_Relative_AddedNeedy_Note a_notes;

    //Элемент списка на экране
    private RecyclerView rv_Notes;

    private DataBase_AppDatabase dataBase;

    private String selectedId;

    @SuppressLint("ValidFragment")
    public Fragment_SeeNotes(String id){
        this.selectedId=id;
    }

    //Фрагмент с блоками заметок
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_seenotes, container, false);

        initializeDataBase();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())  {
                    case R.id.btn_AddNewNote:

                        Intent newnote=new Intent(getContext(), Activity_Dialog_AddNote.class);
                        newnote.putExtra("needy_id", selectedId);
                        startActivity(newnote);

                        break;
                }
            }
        };

        //Инициализация элементов
        rv_Notes=v.findViewById(R.id.rv_Notes);
        btn_AddNote=v.findViewById(R.id.btn_AddNewNote);
        btn_AddNote.setOnClickListener(oclBtn);

        initializeList();
        initializeRecycleView();

        return v;
    }





    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }



    private void initializeList(){
        if(!(dataBase.dao_relative_addedNeedy().getAll()==null)){
            notes=dataBase.dao_relative_addedNeedy_note().getByNeedyId(selectedId);
        }
    }

    private void initializeRecycleView(){
        a_notes=new Adapter_Relative_AddedNeedy_Note(getActivity(), notes,this);
        rv_Notes.setAdapter(a_notes);
        rv_Notes.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void delete(Entity_Relative_AddedNeedy_Note note) {
        dataBase.dao_relative_addedNeedy_note().delete(note);
        initializeList();
        initializeRecycleView();
    }

    @Override
    public void onResume() {
        super.onResume();
        onStart();
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeList();
        initializeRecycleView();
    }
}
