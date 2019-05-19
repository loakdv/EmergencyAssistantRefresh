package com.example.dmitriy.emergencyassistant.Adapters.Needy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.Elements.Element_StateSelect;
import com.example.dmitriy.emergencyassistant.R;

import java.util.List;

public class Adapter_Needy_TaskElement extends RecyclerView.Adapter<Adapter_Needy_TaskElement.ViewHolder> {

    //Объект интерфейса
    private CallBackButtons callback;

    private List<Element_StateSelect> mData;
    private LayoutInflater mInflater;




    // Данные для конструктора
    public Adapter_Needy_TaskElement(
            Context context, List<Element_StateSelect> data,
            CallBackButtons callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
    }

    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void select(Element_StateSelect relative);
    }


    // Поиск элемента который будет располагаться в списке
    @Override
    public Adapter_Needy_TaskElement.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_statevariant, parent, false);
        return new Adapter_Needy_TaskElement.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Element_StateSelect element = mData.get(i);
        viewHolder.text.setText(element.getText());
    }


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
