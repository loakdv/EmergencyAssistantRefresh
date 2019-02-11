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

public class Adapter_AddedRelatives extends RecyclerView.Adapter<Adapter_AddedRelatives.ViewHolder> {
    private List<Entity_Added_Relatives> mData;
    private LayoutInflater mInflater;


    // Данные для конструктора
    public Adapter_AddedRelatives(Context context, List<Entity_Added_Relatives> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // Поиск элемента который будет располагаться в списке
    @Override
    public Adapter_AddedRelatives.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_user, parent, false);
        return new Adapter_AddedRelatives.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull Adapter_AddedRelatives.ViewHolder viewHolder, int position) {

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
        Button btn_Delete;
        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteUser:
                            Activity_Dialog_Users.deleteUser(getLayoutPosition());
                            break;
                    }
                }
            };
            id=itemView.findViewById(R.id.tv_UserListId);
            btn_Delete=itemView.findViewById(R.id.btn_DeleteUser);
            btn_Delete.setOnClickListener(oclBtn);


        }
    }




}
