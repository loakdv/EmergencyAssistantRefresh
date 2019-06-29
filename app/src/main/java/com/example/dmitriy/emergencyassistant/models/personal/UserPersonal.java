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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
        return SocialFactors;
    }

    public void setSocialFactors(List<SocialFactor> socialFactors) {
        SocialFactors = socialFactors;
    }
}
