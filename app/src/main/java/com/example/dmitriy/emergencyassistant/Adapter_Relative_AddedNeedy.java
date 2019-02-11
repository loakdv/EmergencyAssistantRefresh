package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Adapter_Relative_AddedNeedy extends RecyclerView.Adapter<Adapter_Relative_AddedNeedy.ViewHolder> {
    private List<Entity_Relative_AddedNeedy> mData;
    private LayoutInflater mInflater;
    Entity_Relative_AddedNeedy needy;



    // Данные для конструктора
    public Adapter_Relative_AddedNeedy(Context context, List<Entity_Relative_AddedNeedy> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // Поиск элемента который будет располагаться в списке
    @Override
    public Adapter_Relative_AddedNeedy.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_needy, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull Adapter_Relative_AddedNeedy.ViewHolder viewHolder, int position) {
         needy = mData.get(position);
    }

    // Общее количество элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        Button select;
        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_SelectNeedy:
                            break;
                    }
                }
            };
            id=itemView.findViewById(R.id.tv_NeedyListId);
            select=itemView.findViewById(R.id.btn_SelectNeedy);
            select.setOnClickListener(oclBtn);


        }
    }




}
