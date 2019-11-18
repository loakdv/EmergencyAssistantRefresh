/*
 *
 *  Created by Dmitry Garmyshev on 8/18/19 10:33 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/17/19 11:38 AM
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

    @SerializedName("SPECIALIST")
    @Expose
    SPECIALIST,

    @SerializedName("ANALYST")
    @Expose
    ANALYST,

    @SerializedName("CHIEF")
    @Expose
    CHIEF,

    @SerializedName("DIRECTOR")
    @Expose
    DIRECTOR,

    @SerializedName("ACCOUNTANT")
    @Expose
    ACCOUNTANT,

    @SerializedName("ADMIN")
    @Expose
    ADMIN;

    UserRole() {
    }
}
