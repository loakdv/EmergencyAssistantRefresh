package com.example.dmitriy.emergencyassistant.Adapters;

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
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy_Task;

import java.util.List;

public class Adapter_Volunteer_NeedyList extends RecyclerView.Adapter<Adapter_Volunteer_NeedyList.ViewHolder> {




    //Объект интерфейса
    private CallBackButtons callback;
    private Entity_Volunteer_AddedNeedy needy;
    private DataBase_AppDatabase dataBase;

    private List<Entity_Volunteer_AddedNeedy> mData;
    private LayoutInflater mInflater;




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void setTask(Entity_Volunteer_AddedNeedy needy);
    }




    // Данные для конструктора
    public Adapter_Volunteer_NeedyList (Context context,
                                  List<Entity_Volunteer_AddedNeedy> data,
                                  CallBackButtons callback) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;

        initializeDatabase(context);

    }



    private void initializeDatabase(Context context){
        dataBase = Room.databaseBuilder(context,
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    // Поиск элемента который будет располагаться в списке
    @Override
    public Adapter_Volunteer_NeedyList.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        View view = mInflater.inflate(
                R.layout.element_volunteer_needy, parent, false);

        return new Adapter_Volunteer_NeedyList.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull Adapter_Volunteer_NeedyList.ViewHolder viewHolder, int position) {

        needy=mData.get(position);

        viewHolder.name.setText(needy.getName());
        viewHolder.middlename.setText(needy.getMiddlename());
        viewHolder.surname.setText(needy.getSurname());
        viewHolder.id.setText(needy.getNeedyId());

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
