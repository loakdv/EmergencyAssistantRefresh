package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Volunteer_Map extends Fragment {

    Fragment_Volunteer_Main.onChangeVolunFrag changeFrag;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeFrag=(Fragment_Volunteer_Main.onChangeVolunFrag) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_map, container, false);
        Intent intent;
        intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:43.10562 ,131.87353"));
        startActivity(intent);
        return v;
    }
}
