package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Adapter_Volunteer_TaskList extends RecyclerView.Adapter<Adapter_Volunteer_TaskList.ViewHolder> {


    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{

    }


    CallBackButtons callback;
    Entity_Volunteer_AddedNeedy_Task task;
    DataBase_AppDatabase dataBase;

    private List<Entity_Volunteer_AddedNeedy_Task> mData;
    private LayoutInflater mInflater;




    // Данные для конструктора
    public Adapter_Volunteer_TaskList (Context context,
                                        List<Entity_Volunteer_AddedNeedy_Task> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;


        dataBase = Room.databaseBuilder(context,
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    // Поиск элемента который будет располагаться в списке
    @Override
    public Adapter_Volunteer_TaskList.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        View view = mInflater.inflate(
                R.layout.element_volunteer_task, parent, false);

        return new Adapter_Volunteer_TaskList.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull Adapter_Volunteer_TaskList.ViewHolder viewHolder, int position) {
        task=mData.get(position);



        viewHolder.taskName.setText(task.getName());
        viewHolder.taskReview.setText(task.getAbout());



    }




    // Общее количество элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }




    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskName;
        TextView taskReview;

        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_Volunteer_Needy_Tasks:
                            break;
                    }


                }

            };

            taskName=itemView.findViewById(R.id.tv_TaskName);
            taskReview=itemView.findViewById(R.id.tv_TaskReview);

        }
    }

}
