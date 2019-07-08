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
