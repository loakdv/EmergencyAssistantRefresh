package com.example.dmitriy.emergencyassistant.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.Elements.Element_VolunteerForSelect;
import com.example.dmitriy.emergencyassistant.R;

import java.util.List;

public class Adapter_VolunteerForSelect extends RecyclerView.Adapter<Adapter_VolunteerForSelect.ViewHolder> {

    private Element_VolunteerForSelect volunteerForSelect;
    private CallbackButton callbackButton;

    private List<Element_VolunteerForSelect> mData;
    private LayoutInflater layoutInflater;


    public interface CallbackButton{
        void selectVolunteer(String id);
    }

    public Adapter_VolunteerForSelect(Context context,
                                      List<Element_VolunteerForSelect> data, CallbackButton callback) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callbackButton = callback;
    }

    @NonNull
    @Override
    public Adapter_VolunteerForSelect.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.element_volunteer_for_select, viewGroup, false);
        return new Adapter_VolunteerForSelect.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Adapter_VolunteerForSelect.ViewHolder viewHolder, int i) {
        volunteerForSelect = mData.get(i);

        viewHolder.tvAbout.setText(volunteerForSelect.getReview());
        viewHolder.tvInitials.setText(volunteerForSelect.getInitials());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvInitials;
        TextView tvAbout;
        Button btnSelect;

        ViewHolder(final View itemView){
            super(itemView);

            View.OnClickListener oclBtn = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_Select_Volunteer:
                            callbackButton.selectVolunteer(mData.get(getLayoutPosition()).getId());
                            break;
                    }
                }
            };
            tvInitials = itemView.findViewById(R.id.tv_Vol_Initials);
            tvAbout = itemView.findViewById(R.id.tv_Vol_Review);
            btnSelect = itemView.findViewById(R.id.btn_Select_Volunteer);
            btnSelect.setOnClickListener(oclBtn);
        }
    }
}
