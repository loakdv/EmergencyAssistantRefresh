package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class FirebaseRelative {

    public String profile_id;
    public boolean doctor;

    public FirebaseRelative(String profile_id, boolean doctor){
        this.profile_id=profile_id;
        this.doctor=doctor;
    }

    public FirebaseRelative(String uid){}

    public FirebaseRelative(){}

    public String getProfile_id() {
        return this.profile_id;
    }

    public boolean isDoctor() {
        return this.doctor;
    }

}
