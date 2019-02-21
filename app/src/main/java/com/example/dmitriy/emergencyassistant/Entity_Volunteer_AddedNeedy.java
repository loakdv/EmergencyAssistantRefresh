package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Volunteer.class, parentColumns = "id", childColumns = "volunteer_id", onDelete = CASCADE),
        indices = {@Index(value = "volunteer_id", unique = false)})
public class Entity_Volunteer_AddedNeedy {

    @PrimaryKey (autoGenerate = true)
    public long id;

    public long volunteer_id;
    public String name;
    public String surname;
    public String middlename;
    public String adress;

    public Entity_Volunteer_AddedNeedy(String name, String surname, String middlename, String adress, long volunteer_id){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.adress=adress;
        this.volunteer_id=volunteer_id;
    }

    public long getId() {
        return this.id;
    }

    public long getVolunteer_id() {
        return this.volunteer_id;
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

    public String getAdress() {
        return this.adress;
    }
}
