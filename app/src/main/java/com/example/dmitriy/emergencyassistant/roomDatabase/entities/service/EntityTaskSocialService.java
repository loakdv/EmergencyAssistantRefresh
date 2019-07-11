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

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.util.Date;

@Entity
public class EntityTaskSocialService {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("socialService")
    @Expose
    private EntitySocialService socialService;

    @SerializedName("needy")
    @Expose
    private EntityUser needy;

    @SerializedName("employee")
    @Expose
    private EntityUser employee;

    @SerializedName("dateCreate")
    @Expose
    private Date dateCreate;

    @SerializedName("dateStart")
    @Expose
    private Date dateStart;

    @SerializedName("dateStop")
    @Expose
    private Date dateStop;

    @SerializedName("duration")
    @Expose
    private Duration duration;

    @SerializedName("enable")
    @Expose
    private boolean enable = true;

    public EntityTaskSocialService() {}

    public EntityTaskSocialService(EntitySocialService socialService, EntityUser needy) {
        this.socialService = socialService;
        this.needy = needy;
        this.dateCreate = new Date();
    }

    public EntityTaskSocialService(EntitySocialService socialService, EntityUser needy, EntityUser employee) {
        this(socialService, needy);
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntitySocialService getSocialService() {
        return socialService;
    }

    public void setSocialService(EntitySocialService socialService) {
        this.socialService = socialService;
    }

    public EntityUser getNeedy() {
        return needy;
    }

    public void setNeedy(EntityUser needy) {
        this.needy = needy;
    }

    public EntityUser getEmployee() {
        return employee;
    }

    public void setEmployee(EntityUser employee) {
        this.employee = employee;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateStop() {
        return dateStop;
    }

    public void setDateStop(Date dateStop) {
        this.dateStop = dateStop;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
