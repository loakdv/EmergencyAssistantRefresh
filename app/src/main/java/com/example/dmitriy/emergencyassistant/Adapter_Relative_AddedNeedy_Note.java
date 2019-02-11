package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Adapter_Relative_AddedNeedy_Note extends RecyclerView.Adapter<Adapter_Relative_AddedNeedy_Note.ViewHolder> {

    private List<Entity_Relative_AddedNeedy_Note> mData;
    private LayoutInflater mInflater;



    // Данные для конструктора
    public Adapter_Relative_AddedNeedy_Note(Context context, List<Entity_Relative_AddedNeedy_Note> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // Поиск элемента который будет располагаться в списке
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_note, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    // Общее количество элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        Button delete;
        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteNote:
                            Fragment_SeeNotes.removeNote(getLayoutPosition());
                            break;
                    }
                }
            };

            delete=itemView.findViewById(R.id.btn_DeleteNote);
            delete.setOnClickListener(oclBtn);
            myTextView = itemView.findViewById(R.id.tv_Note);

        }
    }


}
