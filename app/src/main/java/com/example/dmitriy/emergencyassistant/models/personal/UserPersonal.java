package com.example.dmitriy.emergencyassistant.models.personal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class UserPersonal {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("active")
    @Expose
    private boolean active;

    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("middlename")
    @Expose
    private String middlename;

    @SerializedName("lastname")
    @Expose
    private String lastname;

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

    @SerializedName("SocialFactors")
    @Expose
    private List<SocialFactor> SocialFactors;
}
