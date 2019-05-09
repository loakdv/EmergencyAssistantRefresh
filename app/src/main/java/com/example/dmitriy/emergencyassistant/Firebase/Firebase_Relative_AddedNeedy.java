package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class Firebase_Relative_AddedNeedy {

    public String name;
    public String surname;
    public String middlename;
    public String info;

    public Firebase_Relative_AddedNeedy(String name, String surname, String middlename, String info){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.info=info;
    }

    public Firebase_Relative_AddedNeedy(){}

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
