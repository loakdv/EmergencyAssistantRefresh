package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class FirebaseVolunteerNeedy {


    private String name;
    private String surname;
    private String middlename;
    private String id;

    public FirebaseVolunteerNeedy(String id, String name, String surname, String middlename){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.id=id;
    }

    public FirebaseVolunteerNeedy(){}

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
}
