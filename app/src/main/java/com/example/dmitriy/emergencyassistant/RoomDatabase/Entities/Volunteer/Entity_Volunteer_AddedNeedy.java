package com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Volunteer.class, parentColumns = "id", childColumns = "volunteer_id", onDelete = CASCADE),
        indices = {@Index(value = "needyId", unique = true)})
public class Entity_Volunteer_AddedNeedy {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String needyId;
    public long volunteer_id;
    public String name;
    public String surname;
    public String middlename;
    public String date;
    public boolean isConfirmed;

    public Entity_Volunteer_AddedNeedy(String id,
                                       String name, String surname,
                                       String middlename, long volunteer_id, String date, boolean isConfirmed){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.volunteer_id=volunteer_id;
        this.needyId=id;
        this.date=date;
        this.isConfirmed=isConfirmed;
    }

    public Entity_Volunteer_AddedNeedy(){}


    public long getId() {
        return this.id;
    }

    public String getNeedyId(){
        return this.needyId;
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

    public String getDate(){return this.date;}

}
