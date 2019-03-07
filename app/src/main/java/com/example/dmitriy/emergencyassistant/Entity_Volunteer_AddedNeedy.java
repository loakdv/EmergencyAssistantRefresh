package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Volunteer.class, parentColumns = "id", childColumns = "volunteer_id", onDelete = CASCADE),
        indices = {@Index(value = "volunteer_id", unique = false)})
public class Entity_Volunteer_AddedNeedy {

    @PrimaryKey
    public long id;

    public long volunteer_id;
    public String name;
    public String surname;
    public String middlename;
    public int year;
    public int month;
    public int day;




    public Entity_Volunteer_AddedNeedy(long id,
                                       int year, int month, int day,
                                       String name, String surname,
                                       String middlename, long volunteer_id){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.year=year;
        this.day=day;
        this.month=month;
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

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

}
