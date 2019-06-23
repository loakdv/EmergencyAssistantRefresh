package com.example.dmitriy.emergencyassistant.retrofit.pojo.volunteer;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJOVolunteerNeedy {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("middlename")
    @Expose
    private String middlename;

    @SerializedName("id")
    @Expose
    private String id;

    public POJOVolunteerNeedy(String id, String name, String surname, String middlename){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.id=id;
    }

    public POJOVolunteerNeedy(){}

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getMiddlename() {
        return this.middlename;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setId(String id) {
        this.id = id;
    }
}
