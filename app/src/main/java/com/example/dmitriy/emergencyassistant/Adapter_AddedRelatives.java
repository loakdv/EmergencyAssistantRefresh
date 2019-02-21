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

    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void deleteUser(Entity_Added_Relatives relative);
    }

    //Объект интерфейса
    CallBackButtons callback;

    // Данные для конструктора
    public Adapter_AddedRelatives(Context context, List<Entity_Added_Relatives> data, CallBackButtons callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
    }

    // Поиск элемента который будет располагаться в списке
    @Override
    public Adapter_AddedRelatives.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_user, parent, false);
        return new Adapter_AddedRelatives.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull Adapter_AddedRelatives.ViewHolder viewHolder, int position) {
        Entity_Added_Relatives relative=mData.get(position);
        viewHolder.id.setText(Long.toString(relative.getId()));
        viewHolder.name.setText(relative.getName());
        viewHolder.surname.setText(relative.getSurname());
        viewHolder.middlename.setText(relative.getMiddlename());
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
        TextView name;
        TextView surname;
        TextView middlename;
        Button btn_Delete;
        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteUser:
                            callback.deleteUser(mData.get(getLayoutPosition()));
                            break;
                    }
                }
            };
            id=itemView.findViewById(R.id.tv_UserListId);
            name=itemView.findViewById(R.id.tv_AddedRel_Name);
            surname=itemView.findViewById(R.id.tv_AddedRel_Surname);
            middlename=itemView.findViewById(R.id.tv_AddedRel_Middlename);
            btn_Delete=itemView.findViewById(R.id.btn_DeleteUser);
            btn_Delete.setOnClickListener(oclBtn);


        }
    }




}
