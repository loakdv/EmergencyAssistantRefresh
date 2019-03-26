package com.example.dmitriy.emergencyassistant.Firebase;

public class Firebase_Relative {

    public String profile_id;
    public boolean doctor;

    public Firebase_Relative(String profile_id, boolean doctor){
        this.profile_id=profile_id;
        this.doctor=doctor;
    }

    public Firebase_Relative(String uid){}

    public Firebase_Relative(){}

    public String getProfile_id() {
        return this.profile_id;
    }

    public boolean isDoctor() {
        return this.doctor;
    }

}
