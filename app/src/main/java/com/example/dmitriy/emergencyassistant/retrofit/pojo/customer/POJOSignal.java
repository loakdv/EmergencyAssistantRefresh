package com.example.dmitriy.emergencyassistant.retrofit.pojo.customer;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJOSignal {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("type")
    @Expose
    private int type;

    public POJOSignal(String id, int type){
        this.id=id;
        this.type=type;
    }

    public POJOSignal(){}


    public String getId() {
        return this.id;
    }

    public int getType() {
        return this.type;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }
}
