/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 3:29 PM
 *
 */

package com.example.dmitriy.emergencyassistant.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.time.LocalDateTime;

public class SocialService {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("cost")
    @Expose
    private float cost;

    @SerializedName("cityprice")
    @Expose
    private float cityprice;

    @SerializedName("countryprice")
    @Expose
    private float countryprice;

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

    @SerializedName("version")
    @Expose
    private Long version;

    @SerializedName("socialServiceCatalog")
    @Expose
    private SocialServiceCatalog socialServiceCatalog;

    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")

    @SerializedName("dateCreation")
    @Expose
    private LocalDateTime dateCreation;

    @SerializedName("dateEnable")
    @Expose
    private LocalDateTime dateEnable;

    public SocialService() {}

    public SocialService(String title, float cost) {
        this.title = title;
        this.cost = cost;
        this.dateCreation = LocalDateTime.now();
    }

    public SocialService(String title, float cityprice, float countryprice) {
        this.title = title;
        this.cityprice = cityprice;
        this.countryprice = countryprice;
        this.dateCreation = LocalDateTime.now();
    }

    public SocialService(String title, float cost, int periods){
        this(title, cost);
        this.periods = periods;
    }

    public SocialService(Long id, String title, float cost){
        this(title, cost);
        this.id = id;
    }

    @Override
    public String toString() {
        return this.title + " " + this.countryprice + " " + this.cityprice;
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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateEnable() {
        return dateEnable;
    }

    public void setDateEnable(LocalDateTime dateEnable) {
        this.dateEnable = dateEnable;
    }

    public float getCityprice() {
        return cityprice;
    }

    public void setCityprice(float cityprice) {
        this.cityprice = cityprice;
    }

    public float getCountryprice() {
        return countryprice;
    }

    public void setCountryprice(float countryprice) {
        this.countryprice = countryprice;
    }

    public SocialServiceCatalog getSocialServiceCatalog() {
        return socialServiceCatalog;
    }

    public void setSocialServiceCatalog(SocialServiceCatalog socialServiceCatalog) {
        this.socialServiceCatalog = socialServiceCatalog;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
