package com.example.dmitriy.emergencyassistant.Adapters.Login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.Elements.Element_Organization;
import com.example.dmitriy.emergencyassistant.R;

import java.util.List;

public class Adapter_Organizations extends RecyclerView.Adapter<Adapter_Organizations.ViewHolder> {



    private List<Element_Organization> mData;
    private LayoutInflater mInflater;


    //Конструктор для адаптера
    public Adapter_Organizations(Context context, List<Element_Organization> data){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }




    //Получаем элемент содержимого
    @NonNull
    @Override
    public Adapter_Organizations.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int i) {

        View view = mInflater.inflate(R.layout.element_organization,
                viewGroup, false );

        return new Adapter_Organizations.ViewHolder(view);
    }




    //Устанавливаем значения элементу при присоединении
    @Override
    public void onBindViewHolder(@NonNull Adapter_Organizations.ViewHolder viewHolder, int position) {

        Element_Organization organization = mData.get(position);

        viewHolder.tvNumber.setText(organization.getPhoneNumber());
        viewHolder.tvSite.setText(organization.getSite());
        viewHolder.tvName.setText(organization.getName());
    }




    //Получить число элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }




    //Класс холдера
    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView tvName;
        TextView tvSite;
        TextView tvNumber;

        ViewHolder(final View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_OrganizationName);
            tvSite = itemView.findViewById(R.id.tv_OrganizationSite);
            tvNumber = itemView.findViewById(R.id.tv_OrganizationPhone);
        }
    }
}
