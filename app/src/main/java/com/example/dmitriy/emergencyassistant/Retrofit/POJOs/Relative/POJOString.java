package com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Relative;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJOString {

    @SerializedName("id")
    @Expose
    private String id;

    public String getId(){return this.id;}

    public POJOString(String id){
        this.id=id;
    }

    public POJOString(){}
}
