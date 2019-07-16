/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 7:46 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.organization;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@Entity
public class EntityOrganization {


    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("typeOf")
    @Expose
    private String typeOf;

    @SerializedName("address")
    @Expose
    @Embedded
    private List<String> address;

    @SerializedName("phone")
    @Expose
    @Embedded
    private List<String> phone;

    @SerializedName("otherContacts")
    @Expose
    @Embedded
    private List<String> otherContacts;

    @SerializedName("coordinates")
    @Expose
    @Embedded
    private List<String> coordinates;

    @SerializedName("photoUrls")
    @Expose
    @Embedded
    private List<String> photoUrls;

    @SerializedName("openHours")
    @Expose
    @Embedded
    private List<String> openHours;



    @SerializedName("users")
    @Expose
    @Embedded
    private List<EntityUser> users;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("dateCreation")
    @Expose
    @Embedded
    private Date dateCreation;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(String typeOf) {
        this.typeOf = typeOf;
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

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<String> getOpenHours() {
        return openHours;
    }

    public void setOpenHours(List<String> openHours) {
        this.openHours = openHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<EntityUser> getUsers() {
        return users;
    }
}
