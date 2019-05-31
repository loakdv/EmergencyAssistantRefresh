package com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityRelative.class, parentColumns = "id", childColumns = "relative_id", onDelete = CASCADE),
        indices = {@Index(value = "relative_id", unique = false)})
public class EntityRelativeAddedNeedy {

    @PrimaryKey
    @NonNull
    public String id;

    public long relative_id;

    public String name;
    public String surname;
    public String middlename;
    public String info;

    public EntityRelativeAddedNeedy(String name, String surname, String middlename, String info, long relative_id, String id){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.info=info;
        this.relative_id=relative_id;
        this.id=id;
    }

    public String getId() {
        return this.id;
    }

    public long getRelative_id() {
        return this.relative_id;
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

    public String getInfo() {
        return this.info;
    }

}
