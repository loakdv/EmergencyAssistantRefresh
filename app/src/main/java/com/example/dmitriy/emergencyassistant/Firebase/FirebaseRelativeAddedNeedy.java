package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class FirebaseRelativeAddedNeedy {

    public String name;
    public String surname;
    public String middlename;
    public String info;

    public FirebaseRelativeAddedNeedy(String name, String surname, String middlename, String info){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.info=info;
    }

    public FirebaseRelativeAddedNeedy(){}

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

}
