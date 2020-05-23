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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialService;
import com.example.dmitriy.emergencyassistant.model.service.TaskStatus;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.util.List;


public class AdapterVolunteerTaskList extends RecyclerView.Adapter<AdapterVolunteerTaskList.ViewHolder> {




    private CallBackButtons callback;
    private TaskSocialService task;
    private DataBaseAppDatabase dataBase;

    private List<TaskSocialService> mData;
    private LayoutInflater mInflater;
    private Context mContext;







    // Данные для конструктора
    public AdapterVolunteerTaskList(Context context,
                                    List<TaskSocialService> data, CallBackButtons callback) {

        if (context != null){
            this.mContext = context;
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
        void updateTask(TaskSocialService task, int position);
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

        try {

            if(task.getTaskStatus() != null){
                switch (task.getTaskStatus()){
                    case NEW:
                        viewHolder.indicatorView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_more_horiz_green_70dp));
                        break;
                    case CLOSED:
                        viewHolder.indicatorView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_more_horiz_red_70dp));
                        viewHolder.btnDelete.setActivated(false);
                        viewHolder.btnDelete.setVisibility(View.GONE);
                        break;
                    case PROCESSING:
                        viewHolder.indicatorView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_more_horiz_blue_70dp));
                        break;
                    default:
                        viewHolder.indicatorView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_more_horiz_grey_70dp));
                }
            }
            else viewHolder.indicatorView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_more_horiz_grey_70dp));



            viewHolder.taskTime.setText(task.getDateCreate().toString());
            viewHolder.taskName.setText(task.getSocialService().getTitle());
            viewHolder.taskReview.setText(Long.toString(task.getId()));

            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.load_element_anim);
            viewHolder.lnRoot.startAnimation(animation);
        }
        catch (NullPointerException e){
            Toast.makeText(mContext, "Bad id: "+task.getId(), Toast.LENGTH_SHORT).show();
            Log.d("ADAPTER TASKS", "====== NULL OBJECT:");
            Log.d("ADAPTER TASKS", "====== "+task.getId());
            Log.d("ADAPTER TASKS", "====== "+task.getVersion());
        }

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
        LinearLayout lnRoot;
        View indicatorView;


        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteTask:
                            TaskSocialService updatedTaskSocialService = mData.get(getLayoutPosition());
                            if(updatedTaskSocialService.getTaskStatus() != null){
                                switch (updatedTaskSocialService.getTaskStatus()){
                                    case NEW:
                                        updatedTaskSocialService.setTaskStatus(TaskStatus.PROCESSING);
                                        break;
                                    case PROCESSING:
                                        updatedTaskSocialService.setTaskStatus(TaskStatus.PENDING);
                                        break;
                                    case PENDING:
                                        updatedTaskSocialService.setTaskStatus(TaskStatus.SOLVED);
                                        break;
                                    case SOLVED:
                                        updatedTaskSocialService.setTaskStatus(TaskStatus.CLOSED);
                                        break;
                                    case CLOSED:
                                        updatedTaskSocialService.setTaskStatus(TaskStatus.NEW);
                                        break;
                                }

                                callback.updateTask(updatedTaskSocialService, getLayoutPosition());
                            }
                            else {
                                Toast.makeText(mContext, "Null status!", Toast.LENGTH_SHORT).show();
                                updatedTaskSocialService.setTaskStatus(TaskStatus.NEW);
                                callback.updateTask(updatedTaskSocialService, getLayoutPosition());
                            }
                            break;
                    }


                }
            };
            taskTime=itemView.findViewById(R.id.tv_TaskTime);
            taskName=itemView.findViewById(R.id.tv_TaskName);
            taskReview=itemView.findViewById(R.id.tv_TaskReview);
            btnDelete=itemView.findViewById(R.id.btn_DeleteTask);
            btnDelete.setOnClickListener(oclBtn);
            lnRoot = itemView.findViewById(R.id.l_task_list_root);
            indicatorView = itemView.findViewById(R.id.viewTaskIndicator);
        }
    }




}