/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
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
    ADMIN
}
