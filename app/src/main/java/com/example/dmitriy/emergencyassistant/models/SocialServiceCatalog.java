package com.example.dmitriy.emergencyassistant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class SocialServiceCatalog {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("socialServices")
    @Expose
    private List<SocialService> socialServices;

    @SerializedName("organization")
    @Expose
    private Organization organization;

    @SerializedName("free")
    @Expose
    private boolean free;

    @SerializedName("enable")
    @Expose
    private boolean enable = true;

    @SerializedName("dateCreation")
    @Expose
    private Date dateCreation;

    @SerializedName("dateEnable")
    @Expose
    private List<Date> dateEnable;

    public SocialServiceCatalog() {    }

    public SocialServiceCatalog(String title, List<SocialService> socialServices) {
        this.title = title;
        this.socialServices = socialServices;
    }

    public SocialServiceCatalog(String id, String title, List<SocialService> socialServices) {
        this(title,socialServices);
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

    public List<SocialService> getSocialServices() {
        return socialServices;
    }

    public void setSocialServices(List<SocialService> socialServices) {
        this.socialServices = socialServices;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
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
