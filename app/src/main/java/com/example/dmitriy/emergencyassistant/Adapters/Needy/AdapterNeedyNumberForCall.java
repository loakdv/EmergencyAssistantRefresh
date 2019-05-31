package com.example.dmitriy.emergencyassistant.Adapters.Needy;

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

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyAddedPhoneNumbers;

import java.util.List;

public class AdapterNeedyNumberForCall extends RecyclerView.Adapter<AdapterNeedyNumberForCall.ViewHolder> {


    private EntityNeedyAddedPhoneNumbers number;
    private DataBaseAppDatabase dataBase;


    //Объект интерфейса
    private CallBackButtons callback;


    private List<EntityNeedyAddedPhoneNumbers> mData;
    private LayoutInflater mInflater;




    //Конструктор для адаптера
    public AdapterNeedyNumberForCall(
            Context context, List<EntityNeedyAddedPhoneNumbers> data,
            CallBackButtons callback){
        this.mInflater=LayoutInflater.from(context);
        this.mData=data;
        this.callback=callback;

        initializeDatabase(context);

    }




    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void call(EntityNeedyAddedPhoneNumbers number);
    }




    private void initializeDatabase(Context context){
        dataBase = Room.databaseBuilder(context,
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }


    //Получаем элемент содержимого
    @NonNull
    @Override
    public AdapterNeedyNumberForCall.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=mInflater.inflate(R.layout.element_callnumber, viewGroup, false );
        return new AdapterNeedyNumberForCall.ViewHolder(view);
    }




    //Устанавливаем значения элементу при присоединении
    @Override
    public void onBindViewHolder(@NonNull AdapterNeedyNumberForCall.ViewHolder viewHolder, int position) {
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
