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
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedyTask;

import java.util.List;

public class AdapterVolunteerNeedyList extends RecyclerView.Adapter<AdapterVolunteerNeedyList.ViewHolder> {




    //Объект интерфейса
    private CallBackButtons callback;
    private EntityVolunteerAddedNeedy needy;
    private DataBaseAppDatabase dataBase;

    private List<EntityVolunteerAddedNeedy> mData;
    private LayoutInflater mInflater;




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void setTask(EntityVolunteerAddedNeedy needy);
    }




    // Данные для конструктора
    public AdapterVolunteerNeedyList(Context context,
                                     List<EntityVolunteerAddedNeedy> data,
                                     CallBackButtons callback) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;

        initializeDatabase(context);

    }



    private void initializeDatabase(Context context){
        dataBase = Room.databaseBuilder(context,
                DataBaseAppDatabase.class, "note_database").
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

        needy=mData.get(position);

        viewHolder.name.setText(needy.getName());
        viewHolder.middlename.setText(needy.getMiddlename());
        viewHolder.surname.setText(needy.getSurname());
        viewHolder.id.setText(needy.getNeedyId());

        List<EntityVolunteerAddedNeedyTask> tasks = dataBase.dao_volunteer_addedNeedy_task().getByNeedyId(needy.getNeedyId());
        int size = tasks.size();
        viewHolder.taskCounter.setText(Integer.toString(size));

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
                            callback.setTask(mData.get(getLayoutPosition()));
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


}
