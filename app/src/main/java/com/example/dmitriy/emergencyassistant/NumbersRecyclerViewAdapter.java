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

public class NumbersRecyclerViewAdapter extends RecyclerView.Adapter<NumbersRecyclerViewAdapter.ViewHolder> {

    private List<AddedNumber> mData;
    private LayoutInflater mInflater;
    AddedNumber number;

    //Конструктор для адаптера
    public NumbersRecyclerViewAdapter(Context context, List<AddedNumber> data){
        this.mInflater=LayoutInflater.from(context);
        this.mData=data;
    }

    //Получаем элемент содержимого
    @NonNull
    @Override
    public NumbersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=mInflater.inflate(R.layout.element_phonenumber, viewGroup, false );
        return new ViewHolder(view);
    }

    //Устанавливаем значения элементу при присоединении
    @Override
    public void onBindViewHolder(@NonNull NumbersRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        number=mData.get(position);
        viewHolder.tv_Name.setText(number.getName());
        viewHolder.tv_Number.setText(number.getNumber());
        viewHolder.tv_id.setText(number.getId());
    }

    //Получить число элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //Класс холдера
    public class ViewHolder extends RecyclerView.ViewHolder{

        //Элементы нужного элемента
        Button btn_DeleteNumber;
        Button btn_EditNumber;
        TextView tv_Name;
        TextView tv_Number;
        TextView tv_id;

        ViewHolder(final View itemView){
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteNumber:
                            Activity_Dialog_Numbers.deleteNumber(getLayoutPosition());
                            break;
                        case R.id.btn_EditNumber:

                            break;
                    }
                }
            };


            btn_DeleteNumber=itemView.findViewById(R.id.btn_DeleteNumber);
            btn_DeleteNumber.setOnClickListener(oclBtn);
            btn_EditNumber=itemView.findViewById(R.id.btn_EditNumber);
            btn_EditNumber.setOnClickListener(oclBtn);
            tv_Name=itemView.findViewById(R.id.tv_NumberName);
            tv_Number=itemView.findViewById(R.id.tv_Number);
            tv_id=itemView.findViewById(R.id.tv_NumberId);
        }
    }


}
