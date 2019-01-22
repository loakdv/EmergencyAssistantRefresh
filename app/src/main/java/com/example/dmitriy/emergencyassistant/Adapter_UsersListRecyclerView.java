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

public class Adapter_UsersListRecyclerView extends RecyclerView.Adapter<Adapter_UsersListRecyclerView.ViewHolder> {
    private List<Added_User> mData;
    private LayoutInflater mInflater;
    Added_User users;



    // Данные для конструктора
    public Adapter_UsersListRecyclerView(Context context, List<Added_User> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // Поиск элемента который будет располагаться в списке
    @Override
    public Adapter_UsersListRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_user, parent, false);
        return new Adapter_UsersListRecyclerView.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull Adapter_UsersListRecyclerView.ViewHolder viewHolder, int position) {
        users = mData.get(position);
        viewHolder.id.setText(users.getId());
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
