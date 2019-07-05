package com.example.dmitriy.emergencyassistant.models.user.personal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public enum Employment {

    @SerializedName("EMPLOYEE")
    @Expose
    EMPLOYEE,

    @SerializedName("UNEMPLOYED")
    @Expose
    UNEMPLOYED,

    @SerializedName("STUDENT")
    @Expose
    STUDENT,

    @SerializedName("PUPIL")
    @Expose
    PUPIL,

    @SerializedName("HOUSEWIFE")
    @Expose
    HOUSEWIFE
}
