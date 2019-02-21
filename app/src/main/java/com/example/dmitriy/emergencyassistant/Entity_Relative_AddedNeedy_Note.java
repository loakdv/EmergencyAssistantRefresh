package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Relative_AddedNeedy.class, parentColumns = "id", childColumns = "needy_id", onDelete = CASCADE),
        indices = {@Index(value = "needy_id", unique = false)})
public class Entity_Relative_AddedNeedy_Note {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long needy_id;
    public String text;
    public String date;

    public Entity_Relative_AddedNeedy_Note(String text, String date, long needy_id){
        this.needy_id=needy_id;
        this.text=text;
        this.date=date;
    }

    public long getId() {
        return this.id;
    }

    public long getNeedy_id() {
        return this.needy_id;
    }

    public String getText() {
        return this.text;
    }

    public String getDate() {
        return this.date;
    }
}
