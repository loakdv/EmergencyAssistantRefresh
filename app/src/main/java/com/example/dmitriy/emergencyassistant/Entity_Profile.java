package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Entity_Profile {
    public boolean logged=false;

    @PrimaryKey(autoGenerate = true)
    public long id;
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
    public String phonenumber;
    public String password;

    public Entity_Profile(int type, String surname, String name, String middlename, String phonenumber,
                          String password, boolean logged){
        this.type=type;
        this.surname=surname;
        this.name=name;
        this.middlename=middlename;
        this.phonenumber=phonenumber;
        this.password=password;
        this.logged=logged;
    }

    public boolean isLogged() {
        return this.logged;
    }

    public long getId() {
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

    public String getPhonenumber() {
        return this.phonenumber;
    }



    public String getPassword() {
        return this.password;
    }
}
