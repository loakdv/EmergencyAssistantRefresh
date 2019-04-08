package com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Needy.class, parentColumns = "id", childColumns = "needyId", onDelete = CASCADE),
        indices = {@Index(value = "needyId", unique = false)})
public class Entity_Needy_Volunteer {

    @PrimaryKey
    @NonNull
    public String id;
    public long needyId;
    public String name;
    public String surname;
    public String middlename;
    public String organization;
    public byte[] photo;

    public Entity_Needy_Volunteer(String id, long needyId, String name, String surname, String middlename, String organization, byte[] photo){
        this.id=id;
        this.needyId=needyId;
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.organization=organization;
        this.photo=photo;
    }


    @NonNull
    public String getId() {
        return this.id;
    }

    public long getNeedyId() {
        return this.needyId;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getMiddlename() {
        return this.middlename;
    }

    public String getOrganization(){ return this.organization;}

    public byte[] getPhoto(){return this.photo;}
}