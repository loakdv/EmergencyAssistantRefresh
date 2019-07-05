package com.example.dmitriy.emergencyassistant.models.user.personal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public enum SocialFactor {

    //Инвалид
    @SerializedName("INVALID")
    @Expose
    INVALID,

    //Пенсионер
    @SerializedName("PENSIONER")
    @Expose
    PENSIONER,

    // Одиноко-проживающий
    @SerializedName("LIVINGOWN")
    @Expose
    LIVINGOWN,

    //Вдова
    @SerializedName("WIDOW")
    @Expose
    WIDOW,
}
