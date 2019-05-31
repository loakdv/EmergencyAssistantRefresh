package com.example.dmitriy.emergencyassistant.Adapters.Relative;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelativeAddedNeedy;

import java.util.List;

public class AdapterRelativeAddedNeedy extends RecyclerView.Adapter<AdapterRelativeAddedNeedy.ViewHolder> {


    private EntityRelativeAddedNeedy needy;

    //Объект интерфейса
    private CallBackButtons callback;

    private List<EntityRelativeAddedNeedy> mData;
    private LayoutInflater mInflater;




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void select(EntityRelativeAddedNeedy number);
    }




    // Данные для конструктора
    public AdapterRelativeAddedNeedy(
            Context context, List<EntityRelativeAddedNeedy> data,
            CallBackButtons callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
    }




    // Поиск элемента который будет располагаться в списке
    @Override
    public AdapterRelativeAddedNeedy.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        View view = mInflater.inflate(
                R.layout.element_needy, parent, false);

        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(
            @NonNull AdapterRelativeAddedNeedy.ViewHolder viewHolder,
            int position) {
         needy = mData.get(position);
         viewHolder.id.setText(needy.getId());
         viewHolder.name.setText(needy.getName());
         viewHolder.surname.setText(needy.getSurname());
         viewHolder.middlename.setText(needy.getMiddlename());
    }




    // Общее количество элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }




    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, surname, middlename;
        Button btnSelect;
        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_SelectNeedy:
                            callback.select(mData.get(getLayoutPosition()));
                            break;
                    }
                }
            };
            id=itemView.findViewById(R.id.tv_NeedyListId);
            name=itemView.findViewById(R.id.tv_SeeNedy_Name);
            surname=itemView.findViewById(R.id.tv_SeeNeedy_Surname);
            middlename=itemView.findViewById(R.id.tv_SeeNedy_Middlename);
            btnSelect=itemView.findViewById(R.id.btn_SelectNeedy);
            btnSelect.setOnClickListener(oclBtn);
        }
    }




}
