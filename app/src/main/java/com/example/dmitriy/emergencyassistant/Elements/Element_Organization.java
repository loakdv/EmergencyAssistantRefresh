package com.example.dmitriy.emergencyassistant.Elements;

public class Element_Organization {

    private String name;
    private String phoneNumber;
    private String site;

    public Element_Organization(String name, String phone, String site){
        this.name = name;
        this.phoneNumber = phone;
        this.site = site;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
