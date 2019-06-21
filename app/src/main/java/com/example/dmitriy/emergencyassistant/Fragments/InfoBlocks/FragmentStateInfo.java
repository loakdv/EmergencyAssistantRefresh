package com.example.dmitriy.emergencyassistant.Fragments.InfoBlocks;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Needy.POJOState;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBaseAppDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tooltip.Tooltip;

import java.util.ArrayList;
import java.util.List;

//Фрагмент с блоками состояния(прогрессбарами)

@SuppressLint("ValidFragment")
public class FragmentStateInfo extends Fragment {



    private ProgressBar pb_9;
    private ProgressBar pb_12;
    private ProgressBar pb_15;
    private ProgressBar pb_18;
    private ProgressBar pb_21;

    private List<POJOState> statesList;

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private Button btnHelp;

    String selectedId;

    DataBaseAppDatabase dataBase;

    @SuppressLint("ValidFragment")
    public FragmentStateInfo(String id){
        this.selectedId=id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_seestate, container, false);

        initializeDataBase();


        databaseReference= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();

        pb_9=v.findViewById(R.id.progressBar_9);
        pb_12=v.findViewById(R.id.progressBar12);
        pb_15=v.findViewById(R.id.progressBar15);
        pb_18=v.findViewById(R.id.progressBar18);
        pb_21=v.findViewById(R.id.progressBar21);

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_seestate_help:
                        showTooltip(v, Gravity.BOTTOM, "Здесь будет отображаться " +
                                "состояние пользователя на текущую дату." +
                                "\n \n" +
                                "Данные обновляются 5 раз, с 9:00 до 21:00." +
                                "\n \n" +
                                "Если данные не обновляются или не появляются вовсе, " +
                                "то скорее всего пользователь ещё не вснёс данные о состоянии, " +
                                "или же у него отключена функция отслеживания состояния." +
                                "\n \n" +
                                "(Нажмите на сообщение чтобы закрыть его)");
                        break;
                }
            }
        };

        btnHelp=v.findViewById(R.id.btn_seestate_help);
        btnHelp.setOnClickListener(oclBtn);


        setProgress();
        return v;
    }


    public void setProgress(){



        databaseReference.child("Users").child(selectedId).child("State").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                statesList = new ArrayList<>();

                 /*
                Получение состояний мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                  statesList.add(child.getValue(POJOState.class));


                }


                int states[]=new int[5];

                for(int i=0; i<states.length; i++){
                    states[i]=0;
                }

                for(int i=0; i<statesList.size(); i++){
                    states[i]=statesList.get(i).getPercent();
                }

                initializeState(states);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializeState(int[] states){
        pb_9.setProgress(states[0]);
        pb_12.setProgress(states[1]);
        pb_15.setProgress(states[2]);
        pb_18.setProgress(states[3]);
        pb_21.setProgress(states[4]);
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }



    private void showTooltip(View v, int gravity, String text){

        Tooltip tooltip = new Tooltip.Builder(v).
                setText(text).
                setTextColor(Color.WHITE).
                setGravity(gravity).
                setDismissOnClick(true).
                setBackgroundColor(Color.BLUE).
                setCornerRadius(10f).
                show();

    }


}
