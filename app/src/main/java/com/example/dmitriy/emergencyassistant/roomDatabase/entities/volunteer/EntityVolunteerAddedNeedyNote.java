package com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityVolunteerAddedNeedy.class, parentColumns = "needyId", childColumns = "needy_id", onDelete = CASCADE) ,
        indices = {@Index(value = "needy_id", unique = false)})
public class EntityVolunteerAddedNeedyNote {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String needy_id;
    public String text;
    public String date;

    public EntityVolunteerAddedNeedyNote(String text, String date, String needy_id){
        this.needy_id=needy_id;
        this.text=text;
        this.date=date;
    }

    public long getId() {
        return this.id;
    }

    public String getNeedy_id() {
        return this.needy_id;
    }

    public String getText() {
        return this.text;
    }

    public String getDate() {
        return this.date;
    }
}
