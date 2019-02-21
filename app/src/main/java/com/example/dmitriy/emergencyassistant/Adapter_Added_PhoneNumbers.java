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

    private List<Entity_Added_PhoneNumbers> mData;
    private LayoutInflater mInflater;
    private Context lContext;
    Entity_Added_PhoneNumbers number;
    DataBase_AppDatabase dataBase;

    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void deleteNumber(Entity_Added_PhoneNumbers number);
        void updateNumber(Entity_Added_PhoneNumbers number);
    }

    //Объект интерфейса
    CallBackButtons callback;

    //Конструктор для адаптера
    public Adapter_Added_PhoneNumbers(Context context, List<Entity_Added_PhoneNumbers> data, CallBackButtons callback){
        this.mInflater=LayoutInflater.from(context);
        this.mData=data;
        this.callback=callback;
        this.lContext=context;

        dataBase = Room.databaseBuilder(context,
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }

    //Получаем элемент содержимого
    @NonNull
    @Override
    public Adapter_Added_PhoneNumbers.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=mInflater.inflate(R.layout.element_phonenumber, viewGroup, false );
        return new ViewHolder(view);
    }

    //Устанавливаем значения элементу при присоединении
    @Override
    public void onBindViewHolder(@NonNull Adapter_Added_PhoneNumbers.ViewHolder viewHolder, int position) {
        number=mData.get(position);
        viewHolder.tv_Name.setText(number.getName());
        viewHolder.tv_Number.setText(number.getNumber());
        try {
            byte[] image=number.getImage();
            Bitmap bmp= BitmapFactory.decodeByteArray(image, 0, image.length);
            viewHolder.img_ImageView.setImageBitmap(bmp);
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
        Button btn_DeleteNumber;
        Button btn_EditNumber;
        TextView tv_Name;
        TextView tv_Number;
        TextView tv_id;
        ImageView img_ImageView;

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


            btn_DeleteNumber=itemView.findViewById(R.id.btn_DeleteNumber);
            btn_DeleteNumber.setOnClickListener(oclBtn);
            btn_EditNumber=itemView.findViewById(R.id.btn_EditNumber);
            btn_EditNumber.setOnClickListener(oclBtn);
            tv_Name=itemView.findViewById(R.id.tv_NumberName);
            tv_Number=itemView.findViewById(R.id.tv_Number);
            tv_id=itemView.findViewById(R.id.tv_NumberId);
            img_ImageView=itemView.findViewById(R.id.imgv_PhoneNumberImage);
        }
    }


}
