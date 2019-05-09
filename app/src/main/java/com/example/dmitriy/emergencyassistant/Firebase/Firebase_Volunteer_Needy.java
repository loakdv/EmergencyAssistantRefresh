package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class Firebase_Volunteer_Needy {


    private String name;
    private String surname;
    private String middlename;
    private String id;

    public Firebase_Volunteer_Needy(String id, String name, String surname, String middlename){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.id=id;
    }

    public Firebase_Volunteer_Needy(){}

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
