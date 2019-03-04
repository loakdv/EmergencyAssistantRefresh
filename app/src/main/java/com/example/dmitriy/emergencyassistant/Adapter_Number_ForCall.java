package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_Number_ForCall extends RecyclerView.Adapter<Adapter_Number_ForCall.ViewHolder> {


    Entity_Added_PhoneNumbers number;
    DataBase_AppDatabase dataBase;

    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void call(Entity_Added_PhoneNumbers number);
    }


    //Объект интерфейса
    CallBackButtons callback;


    private List<Entity_Added_PhoneNumbers> mData;
    private LayoutInflater mInflater;




    //Конструктор для адаптера
    public Adapter_Number_ForCall(
            Context context, List<Entity_Added_PhoneNumbers> data,
            CallBackButtons callback){
        this.mInflater=LayoutInflater.from(context);
        this.mData=data;
        this.callback=callback;


        dataBase = Room.databaseBuilder(context,
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    //Получаем элемент содержимого
    @NonNull
    @Override
    public Adapter_Number_ForCall.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=mInflater.inflate(R.layout.element_callnumber, viewGroup, false );
        return new Adapter_Number_ForCall.ViewHolder(view);
    }




    //Устанавливаем значения элементу при присоединении
    @Override
    public void onBindViewHolder(@NonNull Adapter_Number_ForCall.ViewHolder viewHolder, int position) {
        number=mData.get(position);
        viewHolder.tvName.setText(number.getName());
        try {
            byte[] image=number.getImage();
            Bitmap bmp= BitmapFactory.decodeByteArray(image, 0, image.length);
            viewHolder.imgCall.setImageBitmap(bmp);
        }
        catch (Exception e){}
    }




    //Получить число элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }




    //Класс холдера
    public class ViewHolder extends RecyclerView.ViewHolder{

        //Элементы нужного элемента
        Button btnCall;
        ImageView imgCall;
        TextView tvName;


        ViewHolder(final View itemView){
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_CallNumber:
                            callback.call(mData.get(getLayoutPosition()));
                            break;

                    }
                }
            };

            btnCall=itemView.findViewById(R.id.btn_CallNumber);
            btnCall.setOnClickListener(oclBtn);
            imgCall=itemView.findViewById(R.id.img_CallImage);
            tvName=itemView.findViewById(R.id.tv_CallName);

        }
    }


}
