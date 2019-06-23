package com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityVolunteerAddedNeedy.class, parentColumns = "needyId", childColumns = "needy_id", onDelete = CASCADE),
        indices = {@Index(value = "time", unique = true), @Index("needy_id")})
public class EntityVolunteerAddedNeedyTask {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String date;
    public String needy_id;
    public String time;
    public int type;

    public EntityVolunteerAddedNeedyTask(String time, int type, String needy_id, String date){
        this.needy_id=needy_id;
        this.time=time;
        this.type=type;
        this.date=date;
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

    public String getTime() {
        return this.time;
    }

    public String getDate(){return this.date;}


}
