package com.example.dmitriy.emergencyassistant.Fragments.Volunteer;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Volunteer_TaskList;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Profile;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Task;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy_Task;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Fragment_Volunteer_TaskList extends Fragment implements Adapter_Volunteer_TaskList.CallBackButtons{

    public interface OnTasksClick{
        void goBack();
    }

    private OnTasksClick onTasksClick;

    private Adapter_Volunteer_TaskList adapterTasks;
    private RecyclerView recyclerViewTask;
    private List<Entity_Volunteer_AddedNeedy_Task> listTasks;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    private String id;
    private String date;

    private Button btnBack;

    private DataBase_AppDatabase dataBase;

    private List<Firebase_Task> firebaseTasks;





    @SuppressLint("ValidFragment")
    public Fragment_Volunteer_TaskList(String needyID, String date){
        this.id=needyID;
        this.date=date;
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_tasklist, container, false);

        recyclerViewTask=v.findViewById(R.id.rv_VolunteerTasks);

        initializeFirebase();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackTask:
                        onTasksClick.goBack();
                        break;
                }
            }
        };

        initializeDataBase();

        initializeList();


        btnBack=v.findViewById(R.id.btn_BackTask);
        btnBack.setOnClickListener(oclBtn);


        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onTasksClick=(OnTasksClick)context;
    }




    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }




    private void initializeList(){
        if(!(dataBase.dao_volunteer_addedNeedy_task().getAll()==null)){
            listTasks=dataBase.dao_volunteer_addedNeedy_task().getByABC(date, id);
            initializeRecycleView();
        }

    }




    private void initializeRecycleView(){
        adapterTasks=new Adapter_Volunteer_TaskList(getContext(), listTasks, this);
        recyclerViewTask.setAdapter(adapterTasks);
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(getContext()));
    }




    private void initializeFirebase(){
        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }




    @Override
    public void confirmTask(final String needyID, final String date, String time, Entity_Volunteer_AddedNeedy_Task task) {
        FirebaseUser user=mAuth.getCurrentUser();

        dataBase.dao_volunteer_addedNeedy_task().delete(task);

        databaseReference.child("Users").child(needyID).child("Tasks").child("Task").child(date).child(time).removeValue();
        initializeList();

        checkBase(user.getUid(), date);
    }




    private void checkBase(String userId, String date){
        if (adapterTasks.getItemCount()==0){
            databaseReference.child("Users").child(id).child("Tasks").child("Task").child(date).setValue(null);
            if(dataBase.dao_volunteer_addedNeedy_task().getByDate(date)==null){
                databaseReference.child("Users").child(userId).child(date).child("Profiles").setValue(null);
            }
        }
    }


}
