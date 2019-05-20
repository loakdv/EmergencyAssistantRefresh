package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class FirebaseVolunteer {


    public String profile_id;
    public String organization;

    public FirebaseVolunteer(String organization, String profile_id){
        this.profile_id=profile_id;
        this.organization=organization;
    }

    public FirebaseVolunteer(){}

    public String getProfile_id() {
        return this.profile_id;
    }

    public String getOrganization() {
        return this.organization;
    }

}
