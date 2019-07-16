/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 7:44 PM
 *
 */

package com.example.dmitriy.emergencyassistant.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public enum UserRole {

    @SerializedName("HARDUP")
    @Expose
    HARDUP,

    @SerializedName("EMPLOYEE")
    @Expose
    EMPLOYEE,

    @SerializedName("ADMIN")
    @Expose
    ADMIN;

    UserRole(){}
}
