package com.example.dmitriy.emergencyassistant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Organization {

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
    private List<String> address;

    @SerializedName("phone")
    @Expose
    private List<String> phone;

    @SerializedName("otherContacts")
    @Expose
    private List<String> otherContacts;

    @SerializedName("coordinates")
    @Expose
    private List<String> coordinates;

    @SerializedName("photoUrls")
    @Expose
    private List<String> photoUrls;

    @SerializedName("openHours")
    @Expose
    private List<String> openHours;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("dateCreation")
    @Expose
    private Date dateCreation;
}
