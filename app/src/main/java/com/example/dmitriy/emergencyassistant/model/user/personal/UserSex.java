/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 7:46 PM
 *
 */

package com.example.dmitriy.emergencyassistant.model.user.personal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public enum UserSex {
    @SerializedName("MALE")
    @Expose
    MALE,

    @SerializedName("FEMALE")
    @Expose
    FEMALE,

    @SerializedName("THIRD")
    @Expose
    THIRD;

    UserSex(){}
}
