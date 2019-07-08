package com.example.dmitriy.emergencyassistant.roomDatabase.entities.service;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.organization.EntityOrganization;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class EntitySocialServiceCatalog {

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

    @SerializedName("socialServices")
    @Expose
    private List<EntitySocialService> socialServices;

    @SerializedName("organization")
    @Expose
    private EntityOrganization organization;

    @SerializedName("free")
    @Expose
    private boolean free;

    @SerializedName("enable")
    @Expose
    private boolean enable = true;

    @SerializedName("dateCreation")
    @Expose
    private LocalDateTime dateCreation;

    @SerializedName("dateCreation")
    @Expose
    private LocalDateTime dateEnable;



    public EntitySocialServiceCatalog() {    }

    public EntitySocialServiceCatalog(String title, List<EntitySocialService> socialServices) {
        this.title = title;
        this.socialServices = socialServices;
    }

    public EntitySocialServiceCatalog(Long id, String title, List<EntitySocialService> socialServices) {
        this(title,socialServices);
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

    public List<EntitySocialService> getSocialServices() {
        return socialServices;
    }

    public void setSocialServices(List<EntitySocialService> socialServices) {
        this.socialServices = socialServices;
    }

    public EntityOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(EntityOrganization organization) {
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
}
