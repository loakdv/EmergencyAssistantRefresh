/*
 *
 *  Created by Dmitry Garmyshev on 8/19/19 5:18 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/18/19 12:02 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.volunteer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmitriy.emergencyassistant.R;

public class FragmentVolunteerProfile extends Fragment {


    private View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_volunteer_profile, container, false);
        return v;
    }
}
