package com.example.dmitriy.emergencyassistant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.util.Date;

public class TaskSocialService {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("socialService")
    @Expose
    private SocialService socialService;

    @SerializedName("needy")
    @Expose
    private User needy;

    @SerializedName("employee")
    @Expose
    private User employee;

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

    public TaskSocialService() {}

    public TaskSocialService(SocialService socialService, User needy) {
        this.socialService = socialService;
        this.needy = needy;
        this.dateCreate = new Date();
    }

    public TaskSocialService(String id, SocialService socialService, User needy){
        this(socialService, needy);
        this.id = id;
    }

    public TaskSocialService(SocialService socialService, User needy, User employee) {
        this(socialService, needy);
        this.employee = employee;
    }

    public TaskSocialService(String id, SocialService socialService, User needy, User employee){
        this(id, socialService, needy);
        this.employee = employee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SocialService getSocialService() {
        return socialService;
    }

    public void setSocialService(SocialService socialService) {
        this.socialService = socialService;
    }

    public User getNeedy() {
        return needy;
    }

    public void setNeedy(User needy) {
        this.needy = needy;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
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
