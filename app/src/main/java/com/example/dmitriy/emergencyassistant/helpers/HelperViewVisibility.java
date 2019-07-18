/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 8:05 PM
 *
 */

package com.example.dmitriy.emergencyassistant.helpers;

import android.view.View;

public class HelperViewVisibility {


    public static void setVisible(View v){
        v.setVisibility(View.VISIBLE);
    }

    public static void setGone(View v){
        v.setVisibility(View.GONE);
    }
}
