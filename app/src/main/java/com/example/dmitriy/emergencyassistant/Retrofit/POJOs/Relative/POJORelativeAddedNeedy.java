package com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Relative;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJORelativeAddedNeedy {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("surname")
    @Expose
    public String surname;

    @SerializedName("middlename")
    @Expose
    public String middlename;

    @SerializedName("info")
    @Expose
    public String info;

    public POJORelativeAddedNeedy(String name, String surname, String middlename, String info){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.info=info;
    }

    public POJORelativeAddedNeedy(){}

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getMiddlename() {
        return this.middlename;
    }

    public String getInfo() {
        return this.info;
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

    public void setInfo(String info) {
        this.info = info;
    }
}
