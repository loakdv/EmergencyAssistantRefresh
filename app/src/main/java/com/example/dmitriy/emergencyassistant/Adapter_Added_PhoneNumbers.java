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

public class Adapter_Added_PhoneNumbers extends RecyclerView.Adapter<Adapter_Added_PhoneNumbers.ViewHolder> {




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void deleteNumber(Entity_Added_PhoneNumbers number);
        void updateNumber(Entity_Added_PhoneNumbers number);
    }

    Entity_Added_PhoneNumbers number;
    DataBase_AppDatabase dataBase;

    //Объект интерфейса
    CallBackButtons callback;

    private List<Entity_Added_PhoneNumbers> mData;
    private LayoutInflater mInflater;





    //Конструктор для адаптера
    public Adapter_Added_PhoneNumbers(Context context,
                                      List<Entity_Added_PhoneNumbers> data,
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
    public Adapter_Added_PhoneNumbers.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int i) {

        View view=mInflater.inflate(R.layout.element_phonenumber,
                viewGroup, false );

        return new ViewHolder(view);
    }




    //Устанавливаем значения элементу при присоединении
    @Override
    public void onBindViewHolder(@NonNull Adapter_Added_PhoneNumbers.ViewHolder viewHolder, int position) {
        number=mData.get(position);
        viewHolder.tvName.setText(number.getName());
        viewHolder.tvNumber.setText(number.getNumber());
        try {
            byte[] image=number.getImage();
            Bitmap bmp= BitmapFactory.decodeByteArray(image, 0, image.length);
            viewHolder.imgImageView.setImageBitmap(bmp);
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
        Button btnDeleteNumber;
        Button btnEditNumber;
        TextView tvName;
        TextView tvNumber;
        TextView tvId;
        ImageView imgImageView;

        ViewHolder(final View itemView){
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteNumber:
                            callback.deleteNumber(mData.get(getLayoutPosition()));
                            break;
                        case R.id.btn_EditNumber:

                            break;
                    }
                }
            };


            btnDeleteNumber=itemView.findViewById(R.id.btn_DeleteNumber);
            btnDeleteNumber.setOnClickListener(oclBtn);
            btnEditNumber=itemView.findViewById(R.id.btn_EditNumber);
            btnEditNumber.setOnClickListener(oclBtn);
            tvName=itemView.findViewById(R.id.tv_NumberName);
            tvNumber=itemView.findViewById(R.id.tv_Number);
            tvId=itemView.findViewById(R.id.tv_NumberId);
            imgImageView=itemView.findViewById(R.id.imgv_PhoneNumberImage);
        }
    }




}
