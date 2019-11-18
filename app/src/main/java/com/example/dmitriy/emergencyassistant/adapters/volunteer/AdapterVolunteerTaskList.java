/*
 *
 *  Created by Dmitry Garmyshev on 8/19/19 5:18 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/18/19 10:49 AM
 *
 */

package com.example.dmitriy.emergencyassistant.adapters.volunteer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.util.List;

public class AdapterVolunteerTaskList extends RecyclerView.Adapter<AdapterVolunteerTaskList.ViewHolder> {




    private CallBackButtons callback;
    private TaskSocialService task;
    private DataBaseAppDatabase dataBase;

    private List<TaskSocialService> mData;
    private LayoutInflater mInflater;




    // Данные для конструктора
    public AdapterVolunteerTaskList(Context context,
                                    List<TaskSocialService> data, CallBackButtons callback) {

        if (context != null){
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.callback=callback;
        }


        /*
        dataBase = Room.databaseBuilder(context,
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
         */
    }




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        void deleteTask(TaskSocialService task);
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
        viewHolder.taskTime.setText(Long.toString(task.getId()));
        viewHolder.taskName.setText(task.getSocialService().getTitle());

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
                            callback.deleteTask(mData.get(getLayoutPosition()));
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