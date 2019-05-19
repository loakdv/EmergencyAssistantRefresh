package com.example.dmitriy.emergencyassistant.Adapters.Volunteer;

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
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy_Task;

import java.util.List;

public class Adapter_Volunteer_TaskList extends RecyclerView.Adapter<Adapter_Volunteer_TaskList.ViewHolder> {




    private CallBackButtons callback;
    private Entity_Volunteer_AddedNeedy_Task task;
    private DataBase_AppDatabase dataBase;

    private List<Entity_Volunteer_AddedNeedy_Task> mData;
    private LayoutInflater mInflater;

    private String initials;




    // Данные для конструктора
    public Adapter_Volunteer_TaskList (Context context,
                                        List<Entity_Volunteer_AddedNeedy_Task> data, CallBackButtons callback, String initials) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
        this.initials = initials;

        dataBase = Room.databaseBuilder(context,
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        void confirmTask(String needyID, String date, String time, Entity_Volunteer_AddedNeedy_Task task);
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
                            Entity_Volunteer_AddedNeedy_Task needy=mData.get(getLayoutPosition());
                            callback.confirmTask(needy.getNeedy_id(), needy.getDate(), needy.getTime(), mData.get(getLayoutPosition()));
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