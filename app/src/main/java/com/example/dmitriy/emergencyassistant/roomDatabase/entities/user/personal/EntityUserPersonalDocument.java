/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:52 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.personal;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.dmitriy.emergencyassistant.model.user.personal.UserSex;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class EntityUserPersonalDocument {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("codeIssueState")
    @Expose
    private String codeIssueState;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("serial")
    @Expose
    private String serial;


    @SerializedName("authority")
    @Expose
    private String authority;

    @SerializedName("dateIssue")
    @Expose
    private Date dateIssue;

    @SerializedName("dateValidity")
    @Expose
    private Date dateValidity;

    @SerializedName("dateExpiry")
    @Expose
    private Date dateExpiry;

    @SerializedName("natinality")
    @Expose
    private String natinality;

    @SerializedName("userSex")
    @Expose
    private UserSex userSex;

    @SerializedName("placeBirth")
    @Expose
    private String placeBirth;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("dateBirth")
    @Expose
    private Date dateBirth;

    @SerializedName("holderSign")
    @Expose
    private String holderSign;

    @SerializedName("description")
    @Expose
    private String description;

    // Include date
    @SerializedName("dateCreation")
    @Expose
    private Date dateCreation;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCodeIssueState() {
        return codeIssueState;
    }

    public void setCodeIssueState(String codeIssueState) {
        this.codeIssueState = codeIssueState;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public Date getDateValidity() {
        return dateValidity;
    }

    public void setDateValidity(Date dateValidity) {
        this.dateValidity = dateValidity;
    }

    public Date getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(Date dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    public String getNatinality() {
        return natinality;
    }

    public void setNatinality(String natinality) {
        this.natinality = natinality;
    }

    public UserSex getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSex userSex) {
        this.userSex = userSex;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getHolderSign() {
        return holderSign;
    }

    public void setHolderSign(String holderSign) {
        this.holderSign = holderSign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
