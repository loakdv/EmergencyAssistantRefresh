package com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJORelative {

    @SerializedName("profile_id")
    @Expose
    public String profile_id;

    @SerializedName("doctor")
    @Expose
    public boolean doctor;

    public POJORelative(String profile_id, boolean doctor){
        this.profile_id=profile_id;
        this.doctor=doctor;
    }

    public POJORelative(String uid){}

    public POJORelative(){}

    public String getProfile_id() {
        return this.profile_id;
    }

    public boolean isDoctor() {
        return this.doctor;
    }

}
