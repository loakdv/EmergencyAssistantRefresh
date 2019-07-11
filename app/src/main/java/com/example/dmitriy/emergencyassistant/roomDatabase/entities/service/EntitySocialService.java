/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:52 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.service;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class EntitySocialService {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("cityprice")
    @Expose
    private float cityprice;

    @SerializedName("countryprice")
    @Expose
    private float countryprice;

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
    private EntitySocialServiceCatalog socialServiceCatalog;

    @SerializedName("dateCreation")
    @Expose
    private LocalDateTime dateCreation;

    @SerializedName("dateCreation")
    @Expose
    private LocalDateTime dateEnable;


    public EntitySocialService() {}

    public EntitySocialService(String title, float cost) {
        this.title = title;
        this.cost = cost;
        this.dateCreation = LocalDateTime.now();
    }

    public EntitySocialService(String title, float cityprice, float countryprice) {
        this.title = title;
        this.cityprice = cityprice;
        this.countryprice = countryprice;
        this.dateCreation = LocalDateTime.now();
    }

    public EntitySocialService(String title, float cost, int periods){
        this(title, cost);
        this.periods = periods;
    }

    public EntitySocialService(Long id, String title, float cost){
        this(title, cost);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
