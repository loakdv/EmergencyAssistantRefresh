package com.example.dmitriy.emergencyassistant.models.personal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Document {

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
}
