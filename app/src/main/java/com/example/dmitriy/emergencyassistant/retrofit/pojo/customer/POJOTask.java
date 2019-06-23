package com.example.dmitriy.emergencyassistant.retrofit.pojo.customer;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJOTask {

    @SerializedName("needyId")
    @Expose
    private String needyId;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("serviceName")
    @Expose
    private String serviceName;

    @SerializedName("type")
    @Expose
    public int type;

    @SerializedName("date")
    @Expose
    public String date;

    public POJOTask(String needyId, String time, int type, String date, String serviceName){
        this.needyId = needyId;
        this.time=time;
        this.type=type;
        this.date=date;
        this.serviceName = serviceName;
    }

    public POJOTask(){}

    public String getNeedyId() {
        return this.needyId;
    }

    public String getDate(){return this.date;}

    public String getTime() {
        return this.time;
    }

    public int getType(){return this.type;}


    public void setNeedyId(String needyId) {
        this.needyId = needyId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
