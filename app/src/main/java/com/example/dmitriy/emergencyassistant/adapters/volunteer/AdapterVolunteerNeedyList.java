/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/19/19 8:57 PM
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
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.util.List;

public class AdapterVolunteerNeedyList extends RecyclerView.Adapter<AdapterVolunteerNeedyList.ViewHolder> {


    //Объект интерфейса
    private CallBackButtons callback;
    private User user;
    private DataBaseAppDatabase dataBase;

    private List<User> mData;
    private LayoutInflater mInflater;



    // Данные для конструктора
    public AdapterVolunteerNeedyList(Context context,
                                     List<User> data,
                                     CallBackButtons callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
        //initializeDatabase(context);
    }



    private void initializeDatabase(Context context){
        dataBase = Room.databaseBuilder(context,
                DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().build();
    }




    // Поиск элемента который будет располагаться в списке
    @Override
    public AdapterVolunteerNeedyList.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = mInflater.inflate(
                R.layout.element_volunteer_needy, parent, false);
        return new AdapterVolunteerNeedyList.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull AdapterVolunteerNeedyList.ViewHolder viewHolder, int position) {
        user = mData.get(position);
        viewHolder.name.setText(user.getFirstname());
        viewHolder.middlename.setText(user.getLastname());
        viewHolder.surname.setText(user.getMiddlename());
        viewHolder.id.setText(user.getNickname());
        /*
        List<EntityVolunteerAddedNeedyTask> tasks = dataBase.dao_volunteer_addedNeedy_task().getByNeedyId(needy.getNeedyId());
        int size = tasks.size();
        viewHolder.taskCounter.setText(Integer.toString(size));
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
        TextView name;
        TextView middlename;
        TextView surname;
        TextView taskCounter;
        TextView id;
        Button btn_Tasks;

        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_Volunteer_Needy_Tasks:
                            callback.selectUser(mData.get(getLayoutPosition()));
                            break;
                    }
                }
            };
            name=itemView.findViewById(R.id.tv_Volunteer_Needy_Name);
            middlename=itemView.findViewById(R.id.tv_Volunteer_Needy_Middlename);
            surname=itemView.findViewById(R.id.tv_Volunteer_Needy_Surname);
            taskCounter=itemView.findViewById(R.id.tv_TaskCounter);
            id=itemView.findViewById(R.id.tv_Volunteer_Needy_ID);
            btn_Tasks=itemView.findViewById(R.id.btn_Volunteer_Needy_Tasks);
            btn_Tasks.setOnClickListener(oclBtn);


        }
    }


    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void selectUser(User user);
    }


}
