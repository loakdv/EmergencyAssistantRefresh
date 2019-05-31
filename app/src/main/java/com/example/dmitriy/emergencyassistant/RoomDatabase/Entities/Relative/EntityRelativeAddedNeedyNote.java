package com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityRelativeAddedNeedy.class, parentColumns = "id", childColumns = "needy_id", onDelete = CASCADE),
        indices = {@Index(value = "needy_id", unique = false)})
public class EntityRelativeAddedNeedyNote {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String needy_id;
    public String text;
    public String date;

    public EntityRelativeAddedNeedyNote(String text, String date, String needy_id){
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
