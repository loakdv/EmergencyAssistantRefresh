package com.example.dmitriy.emergencyassistant.models.personal;

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
    THIRD
}
