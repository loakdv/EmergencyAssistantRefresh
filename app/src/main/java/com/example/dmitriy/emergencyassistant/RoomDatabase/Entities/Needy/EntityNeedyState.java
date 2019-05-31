package com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityNeedy.class, parentColumns = "id", childColumns = "needy_id", onDelete = CASCADE),
        indices = {@Index(value = "needy_id", unique = false)})
public class EntityNeedyState {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long needy_id;

    public int status;


    public String date;

    public EntityNeedyState(int status, String date, long needy_id){
        this.status=status;
        this.date=date;
        this.needy_id=needy_id;
    }


    public long getId() {
        return this.id;
    }

    public long getNeedy_id() {
        return this.needy_id;
    }

    public int getStatus() {
        return this.status;
    }

    public String getDate() {
        return this.date;
    }
}
