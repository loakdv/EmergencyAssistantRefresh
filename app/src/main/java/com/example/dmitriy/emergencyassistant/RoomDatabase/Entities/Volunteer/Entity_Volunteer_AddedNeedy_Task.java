package com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Volunteer_AddedNeedy.class, parentColumns = "needyId", childColumns = "needy_id"),
        indices = {@Index(value = "time", unique = true)})
public class Entity_Volunteer_AddedNeedy_Task {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String date;
    public String needy_id;
    public String time;
    public int type;
    public boolean isConfirmed;

    public Entity_Volunteer_AddedNeedy_Task(String time, int type, String needy_id, String date, boolean confirmed){
        this.needy_id=needy_id;
        this.time=time;
        this.type=type;
        this.date=date;
        this.isConfirmed=confirmed;
    }

    public long getId() {
        return this.id;
    }

    public String getNeedy_id() {
        return this.needy_id;
    }

    public int getType() {
        return this.type;
    }

    public boolean isConfirmed(){return this.isConfirmed;}
    public String getTime() {
        return this.time;
    }

    public String getDate(){return this.date;}


}
