package com.example.dmitriy.emergencyassistant.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.Entity_Relative_AddedNeedy_Note;

import java.util.List;

public class Adapter_Relative_AddedNeedy_Note extends RecyclerView.Adapter<Adapter_Relative_AddedNeedy_Note.ViewHolder> {

    private List<Entity_Relative_AddedNeedy_Note> mData;
    private LayoutInflater mInflater;

    //Объект интерфейса
    private CallBackButtons callback;



    // Данные для конструктора
    public Adapter_Relative_AddedNeedy_Note(Context context, List<Entity_Relative_AddedNeedy_Note> data,CallBackButtons callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
    }




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void delete(Entity_Relative_AddedNeedy_Note note);
    }




    // Поиск элемента который будет располагаться в списке
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_note, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Entity_Relative_AddedNeedy_Note note=mData.get(position);
        holder.myTextView.setText(note.getText());
        holder.date.setText(note.getDate());
    }




    // Общее количество элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }





    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        TextView date;
        Button delete;
        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteNote:
                            callback.delete(mData.get(getLayoutPosition()));
                            break;
                    }
                }
            };

            delete=itemView.findViewById(R.id.btn_DeleteNote);
            delete.setOnClickListener(oclBtn);
            myTextView = itemView.findViewById(R.id.tv_Note);
            date=itemView.findViewById(R.id.tv_NoteDate);

        }
    }


}
