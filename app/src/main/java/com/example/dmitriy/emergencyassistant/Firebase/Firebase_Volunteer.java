package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class Firebase_Volunteer {


    public String profile_id;
    public String organization;

    public Firebase_Volunteer(String organization, String profile_id){
        this.profile_id=profile_id;
        this.organization=organization;
    }

    public Firebase_Volunteer(){}

    public String getProfile_id() {
        return this.profile_id;
    }

    public String getOrganization() {
        return this.organization;
    }

}
