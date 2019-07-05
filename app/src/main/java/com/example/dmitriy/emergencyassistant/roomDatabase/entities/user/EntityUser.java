package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class EntityUser {

    @PrimaryKey
    @NonNull
    public String id;


    public String nickname;
    public String password;

    public boolean enable = true;

    public Date dateCreation;

    

    public int type=0;
    public String surname;
    public String name;
    public String middlename;

    public EntityUser(int type, String surname, String name, String middlename, String email,
                      String password, String id){
        this.type=type;
        this.surname=surname;
        this.name=name;
        this.middlename=middlename;
        this.nickname =email;
        this.password=password;
        this.id=id;

    }


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

    public String getNickname() {
        return this.nickname;
    }



    public String getPassword() {
        return this.password;
    }
}
