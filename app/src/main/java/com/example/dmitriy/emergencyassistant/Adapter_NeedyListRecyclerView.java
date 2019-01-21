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

public class Adapter_NeedyListRecyclerView extends RecyclerView.Adapter<Adapter_NeedyListRecyclerView.ViewHolder> {
    private List<Added_Needy> mData;
    private LayoutInflater mInflater;
    Added_Needy needy;



    // Данные для конструктора
    public Adapter_NeedyListRecyclerView(Context context, List<Added_Needy> data) {
        this.mInflater = LayoutInflater.from(context);

        this.mData = data;
    }

    // Поиск элемента который будет располагаться в списке
    @Override
    public Adapter_NeedyListRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_needy, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull Adapter_NeedyListRecyclerView.ViewHolder viewHolder, int position) {
         needy = mData.get(position);
         viewHolder.id.setText(needy.getId());
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
                            Fragment_SeeNeedyInfo.setInfo(needy.getName(), needy.getSurname(), needy.getMiddlename(), needy.getInfo());
                            Fragment_SeeNeedyInfo.setSelectedNeedy(getLayoutPosition());
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
