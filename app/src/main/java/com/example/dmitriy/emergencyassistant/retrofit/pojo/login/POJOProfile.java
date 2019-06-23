package com.example.dmitriy.emergencyassistant.retrofit.pojo.login;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJOProfile {


    @SerializedName("id")
    @Expose
    public String id;
    /*
    0 - Needy
    1 - Relative
    2 - Doctor
    3 - Volunteer
     */
    @SerializedName("type")
    @Expose
    public int type=0;

    @SerializedName("surname")
    @Expose
    public String surname;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("middlename")
    @Expose
    public String middlename;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("password")
    @Expose
    public String password;

    public POJOProfile(int type, String surname, String name, String middlename, String email,
                       String password, String id){
        this.type=type;
        this.surname=surname;
        this.name=name;
        this.middlename=middlename;
        this.email=email;
        this.password=password;
        this.id=id;
    }

    public POJOProfile(){}

    public String getId() {
        return this.id;
    }

    public int getType() {
        return this.type;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public String getMiddlename() {
        return this.middlename;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
