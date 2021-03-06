/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 3:36 PM
 *
 */

package com.example.dmitriy.emergencyassistant.model.user.personal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class UserPersonal {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("active")
    @Expose
    private boolean active;

    @SerializedName("userSex")
    @Expose
    private UserSex userSex;


    @SerializedName("address")
    @Expose
    private List<String> address;

    @SerializedName("phone")
    @Expose
    private List<String> phone;

    @SerializedName("otherContacts")
    @Expose
    private List<String> otherContacts;


    @SerializedName("documents")
    @Expose
    private List<Document> documents;

    @SerializedName("dateOfBirth")
    @Expose
    private Date dateOfBirth;

    @SerializedName("placeBirth")
    @Expose
    private String placeBirth;

    @SerializedName("placeResidence")
    @Expose
    private String placeResidence;

    @SerializedName("employment")
    @Expose
    private List<Employment> employment;

    @SerializedName("socialFactors")
    @Expose
    private List<SocialFactor> socialFactors;

    public UserPersonal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserSex getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSex userSex) {
        this.userSex = userSex;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getOtherContacts() {
        return otherContacts;
    }

    public void setOtherContacts(List<String> otherContacts) {
        this.otherContacts = otherContacts;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    public String getPlaceResidence() {
        return placeResidence;
    }

    public void setPlaceResidence(String placeResidence) {
        this.placeResidence = placeResidence;
    }

    public List<Employment> getEmployment() {
        return employment;
    }

    public void setEmployment(List<Employment> employment) {
        this.employment = employment;
    }

    public List<SocialFactor> getSocialFactors() {
        return socialFactors;
    }

    public void setSocialFactors(List<SocialFactor> socialFactors) {
        this.socialFactors = socialFactors;
    }
}
