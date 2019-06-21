package com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Needy;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJOState {

    @SerializedName("needyId")
    @Expose
    String needyId;

    @SerializedName("type")
    @Expose
    int type;

    @SerializedName("percent")
    @Expose
    int percent;

    public POJOState(){}

    public POJOState(String needyId, int type, int percent){
        this.needyId=needyId;
        this.type=type;
        this.percent=percent;
    }

    public String getNeedyId() {
        return this.needyId;
    }

    public int getType() {
        return this.type;
    }

    public int getPercent() {
        return this.percent;
    }
}
