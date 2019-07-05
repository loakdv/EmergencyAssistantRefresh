package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityVolunteer.class, parentColumns = "id", childColumns = "volunteer_id", onDelete = CASCADE),
        indices = {@Index(value = "needyId", unique = true), @Index("volunteer_id")})
public class EntityVolunteerAddedNeedy {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String needyId;
    public long volunteer_id;
    public String name;
    public String surname;
    public String middlename;
    public String date;
    public boolean isConfirmed;

    public EntityVolunteerAddedNeedy(String id,
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

    public EntityVolunteerAddedNeedy(){}


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
