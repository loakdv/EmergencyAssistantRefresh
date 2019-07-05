package com.example.dmitriy.emergencyassistant.models.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class SocialService {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("cost")
    @Expose
    private float cost;

    @SerializedName("duration")
    @Expose
    private Duration duration; //SLA duration, plan

    @SerializedName("oneTime")
    @Expose
    private boolean oneTime;

    @SerializedName("periods")
    @Expose
    private int periods;

    @SerializedName("enable")
    @Expose
    private boolean enable = true;

    @SerializedName("dateCreation")
    @Expose
    private Date dateCreation;

    @SerializedName("dateEnable")
    @Expose
    private List<Date> dateEnable;

    public SocialService() {}

    public SocialService(String title, float cost) {
        this.title = title;
        this.cost = cost;
    }

    public SocialService(String id, String title, float cost){
        this(title, cost);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Date> getDateEnable() {
        return dateEnable;
    }

    public void setDateEnable(List<Date> dateEnable) {
        this.dateEnable = dateEnable;
    }
}
