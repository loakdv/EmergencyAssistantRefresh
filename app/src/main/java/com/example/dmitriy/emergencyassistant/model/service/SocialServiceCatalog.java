/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 3:30 PM
 *
 */

package com.example.dmitriy.emergencyassistant.model.service;

import java.time.LocalDateTime;
import java.util.List;
import com.example.dmitriy.emergencyassistant.model.organization.Organization;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialServiceCatalog {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("socialService")
    @Expose
    private List<SocialService> socialService;

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
    private LocalDateTime dateCreation;

    @SerializedName("dateEnable")
    @Expose
    private LocalDateTime dateEnable;

    public SocialServiceCatalog() {    }

    public SocialServiceCatalog(String title, List<SocialService> socialService) {
        this.title = title;
        this.socialService = socialService;
    }

    public SocialServiceCatalog(Long id, String title, List<SocialService> socialServices) {
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

    public List<SocialService> getSocialService() {
        return socialService;
    }

    public void setSocialService(List<SocialService> socialService) {
        this.socialService = socialService;
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


}
