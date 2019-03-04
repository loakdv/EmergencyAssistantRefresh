package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Adapter_Needy_StateSelect extends RecyclerView.Adapter<Adapter_Needy_StateSelect.ViewHolder> {




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void select(Element_StateSelect relative);
    }

    //Объект интерфейса
    CallBackButtons callback;

    private List<Element_StateSelect> mData;
    private LayoutInflater mInflater;




    // Данные для конструктора
    public Adapter_Needy_StateSelect(
            Context context, List<Element_StateSelect> data,
            CallBackButtons callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
    }




    // Поиск элемента который будет располагаться в списке
    @Override
    public Adapter_Needy_StateSelect.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        View view = mInflater.inflate(
                R.layout.element_statevariant, parent, false);

        return new Adapter_Needy_StateSelect.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(
            @NonNull Adapter_Needy_StateSelect.ViewHolder viewHolder, int position) {
        Element_StateSelect state=mData.get(position);
        viewHolder.text.setText(state.getText());
    }




    // Общее количество элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        LinearLayout lout;

        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.lin_StateZone:
                            callback.select(mData.get(getLayoutPosition()));
                            break;
                    }
                }
            };

            text=itemView.findViewById(R.id.tv_StateSelect);
            lout=itemView.findViewById(R.id.lin_StateZone);
            lout.setOnClickListener(oclBtn);

        }
    }




}
