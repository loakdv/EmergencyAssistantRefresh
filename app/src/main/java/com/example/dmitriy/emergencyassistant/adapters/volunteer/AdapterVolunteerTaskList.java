/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 4:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.adapters.volunteer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedyTask;

import java.util.List;

public class AdapterVolunteerTaskList extends RecyclerView.Adapter<AdapterVolunteerTaskList.ViewHolder> {




    private CallBackButtons callback;
    private EntityVolunteerAddedNeedyTask task;
    private DataBaseAppDatabase dataBase;

    private List<EntityVolunteerAddedNeedyTask> mData;
    private LayoutInflater mInflater;

    private String initials;



    // Данные для конструктора
    public AdapterVolunteerTaskList(Context context,
                                    List<EntityVolunteerAddedNeedyTask> data, CallBackButtons callback, String initials) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
        this.initials = initials;

        /*
        dataBase = Room.databaseBuilder(context,
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
         */
    }




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        void confirmTask(String needyID, String date, String time, EntityVolunteerAddedNeedyTask task);
    }




    // Поиск элемента который будет располагаться в списке
    @Override
    public AdapterVolunteerTaskList.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = mInflater.inflate(
                R.layout.element_volunteer_task, parent, false);
        return new AdapterVolunteerTaskList.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull AdapterVolunteerTaskList.ViewHolder viewHolder, int position) {
        task=mData.get(position);

        //Получение данных из класса БД
        /*
        switch (task.getType()){
            case 0:
                viewHolder.taskName.setText("Дом");
                viewHolder.taskReview.setText("Пользователю нужна помощь по дому!");
                break;

            case 1:
                viewHolder.taskName.setText("Магазин");
                viewHolder.taskReview.setText("Пользователю нужна помощь с покупками!");
                break;
            case 2:
                viewHolder.taskName.setText("Дом");
                viewHolder.taskReview.setText(initials+" нуждается в помощи с уборкой по дому!");
                break;
            case 3:
                viewHolder.taskName.setText("Дом");
                viewHolder.taskReview.setText(initials+" нуждается в помощи с мойкой посуды!");
                break;
            case 4:
                viewHolder.taskName.setText("Дом");
                viewHolder.taskReview.setText(initials+" нуждается в помощи с выносом мусора!");
                break;
            case 5:
                viewHolder.taskName.setText("Магазин");
                viewHolder.taskReview.setText(initials+" нуждается в товаре: Хлеб");
                break;
            case 6:
                viewHolder.taskName.setText("Магазин");
                viewHolder.taskReview.setText(initials+" нуждается в товаре: Молоко");
                break;
            case 7:
                viewHolder.taskName.setText("Магазин");
                viewHolder.taskReview.setText(initials+" нуждается в товаре: Яйца");
                break;
            case 8:
                viewHolder.taskName.setText("Магазин");
                viewHolder.taskReview.setText(initials+" нуждается в товаре: Огурцы");
                break;
            case 9:
                viewHolder.taskName.setText("Магазин");
                viewHolder.taskReview.setText(initials+" нуждается в товаре: Помидоры");
                break;

        }

        viewHolder.taskTime.setText(task.getTime());
         */
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
        TextView taskTime;
        Button btnDelete;


        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteTask:
                            EntityVolunteerAddedNeedyTask needy=mData.get(getLayoutPosition());
                            //callback.confirmTask(needy.getNeedy_id(), needy.getDate(), needy.getTime(), mData.get(getLayoutPosition()));
                            break;
                    }


                }
            };
            taskTime=itemView.findViewById(R.id.tv_TaskTime);
            taskName=itemView.findViewById(R.id.tv_TaskName);
            taskReview=itemView.findViewById(R.id.tv_TaskReview);
            btnDelete=itemView.findViewById(R.id.btn_DeleteTask);
            btnDelete.setOnClickListener(oclBtn);
        }
    }




}