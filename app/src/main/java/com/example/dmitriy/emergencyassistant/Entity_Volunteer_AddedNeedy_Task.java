package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Volunteer_AddedNeedy.class, parentColumns = "id", childColumns = "needy_id", onDelete = CASCADE),
        indices = {@Index(value = "needy_id", unique = false)})
public class Entity_Volunteer_AddedNeedy_Task {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long needy_id;
    public String name;
    public String about;
    public String date;

    public Entity_Volunteer_AddedNeedy_Task(String name, String about, String date, long needy_id){
        this.needy_id=needy_id;
        this.name=name;
        this.about=about;
        this.date=date;
    }

    public long getId() {
        return this.id;
    }

    public long getNeedy_id() {
        return this.needy_id;
    }

    public String getName() {
        return this.name;
    }

    public String getAbout() {
        return this.about;
    }

    public String getDate() {
        return this.date;
    }
}
