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
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment_SeeNotes extends Fragment  {

    //нопка для длбавления фрагментов
    Button btn_AddNote;

    //Динамический массив для хранения заметок
    static ArrayList<Note> notes=new ArrayList<Note>();
    //Экзеипляр адаптера
    NoteRecyclerViewAdapter a_notes;

    //Элемент списка на экране
    static RecyclerView rv_Notes;

    //Фрагмент с блоками заметок
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_seenotes, container, false);
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())  {
                    case R.id.btn_AddNewNote:

                        Intent newnote=new Intent(getContext(), Activity_Dialog_AddNote.class);
                        startActivity(newnote);

                        break;
                }
            }
        };

        //Инициализация элементов
        rv_Notes=v.findViewById(R.id.rv_Notes);
        btn_AddNote=v.findViewById(R.id.btn_AddNewNote);
        btn_AddNote.setOnClickListener(oclBtn);

        //астройка адаптера и списка
        a_notes=new NoteRecyclerViewAdapter(getContext(), notes);
        rv_Notes.setAdapter(a_notes);
        rv_Notes.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    //Метод для добавления списка
    public static void addNote(String text){
        notes.add(new Note(text, notes.size()+1));
        rv_Notes.getAdapter().notifyDataSetChanged();
    }

    public static void removeNote(int id){
        notes.remove(id);
        rv_Notes.getAdapter().notifyDataSetChanged();
    }

    private void loadNotes(){
        for(int i=0; i<10; i++){
            addNote("NEW NOTE NEW NOTE"+i);
        }
    }




}
