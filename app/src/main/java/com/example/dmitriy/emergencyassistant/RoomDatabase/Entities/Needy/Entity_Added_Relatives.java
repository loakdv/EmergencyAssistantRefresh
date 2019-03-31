package com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Needy.class, parentColumns = "id", childColumns = "needy_id", onDelete = CASCADE),
        indices = {@Index(value = "needy_id", unique = false)})
public class Entity_Added_Relatives {

    @PrimaryKey
    @NonNull
    public String id;
    public long needy_id;
    public String name;
    public String surname;
    public String middlename;
    public boolean doctor;

    public Entity_Added_Relatives(String name, String surname, String middlename, boolean doctor, long needy_id, String id){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.doctor=doctor;
        this.needy_id=needy_id;
        this.id=id;
    }

    public String getId() {
        return this.id;
    }

    public long getNeedy_id() {
        return this.needy_id;
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

    public boolean isDoctor() {
        return this.doctor;
    }
}
