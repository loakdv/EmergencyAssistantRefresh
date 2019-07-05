package com.example.dmitriy.emergencyassistant.fragments.infoblocks;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.activities.dialogs.adds.ActivityDialogAddNote;
import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerAddedNeedyNote;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedyNote;
import com.tooltip.Tooltip;

import java.util.ArrayList;
import java.util.List;

/*
Фрагмент который отображает список заметок на пользователя
 */

@SuppressLint("ValidFragment")
public class FragmentNotesInfo extends Fragment implements AdapterVolunteerAddedNeedyNote.CallBackButtons {

    //нопка для длбавления фрагментов
    private Button btn_AddNote;
    private Button btnHelp;

    //Динамический массив для хранения заметок
    private List<EntityVolunteerAddedNeedyNote> notes=new ArrayList<EntityVolunteerAddedNeedyNote>();
    //Экзеипляр адаптера
    private AdapterVolunteerAddedNeedyNote aNotes;

    //Элемент списка на экране
    private RecyclerView rvNotes;

    private DataBaseAppDatabase dataBase;

    private String selectedId;

    @SuppressLint("ValidFragment")
    public FragmentNotesInfo(String id){
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

                        Intent newnote=new Intent(getContext(), ActivityDialogAddNote.class);
                        newnote.putExtra("needyId", selectedId);
                        startActivity(newnote);

                        break;

                    case R.id.btn_seenotes_help:
                        showTooltip(v, Gravity.BOTTOM, "Здесь вы можете создавать заметки " +
                                "для каждого пользователя." +
                                "\n \n" +
                                "(Нажмите на сообщение чтобы закрыть его)");
                        break;
                }
            }
        };

        //Инициализация элементов
        rvNotes=v.findViewById(R.id.rv_Notes);
        btn_AddNote=v.findViewById(R.id.btn_AddNewNote);
        btn_AddNote.setOnClickListener(oclBtn);

        btnHelp = v.findViewById(R.id.btn_seenotes_help);
        btnHelp.setOnClickListener(oclBtn);

        initializeList();
        initializeRecycleView();

        return v;
    }





    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }



    private void initializeList(){

        notes.add(new EntityVolunteerAddedNeedyNote("texttexttexttexttexttexttexttexttexttext", "date", "id"));
        notes.add(new EntityVolunteerAddedNeedyNote("texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext", "date", "id"));

        /*
        if(!(dataBase.dao_volunteer_addedNeedy().getAll()==null)){
            notes=dataBase.dao_volunteer_addedNeedy_note().getByNeedyId(selectedId);
        }
         */


    }

    private void initializeRecycleView(){
        aNotes=new AdapterVolunteerAddedNeedyNote(getActivity(), notes,this);
        rvNotes.setAdapter(aNotes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvNotes.setLayoutManager(layoutManager);
    }

    @Override
    public void delete(EntityVolunteerAddedNeedyNote note) {
        dataBase.dao_volunteer_addedNeedy_note().delete(note);
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
