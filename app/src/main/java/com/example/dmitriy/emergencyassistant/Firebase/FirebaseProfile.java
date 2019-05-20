package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class FirebaseProfile {

    public String id;
    /*
    0 - Needy
    1 - Relative
    2 - Doctor
    3 - Volunteer
     */
    public int type=0;
    public String surname;
    public String name;
    public String middlename;
    public String email;
    public String password;

    public FirebaseProfile(int type, String surname, String name, String middlename, String email,
                           String password, String id){
        this.type=type;
        this.surname=surname;
        this.name=name;
        this.middlename=middlename;
        this.email=email;
        this.password=password;
        this.id=id;
    }

    public FirebaseProfile(){}

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
}
