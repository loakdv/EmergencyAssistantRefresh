package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Relative.class, parentColumns = "id", childColumns = "relative_id", onDelete = CASCADE))
public class Entity_Relative_AddedNeedy {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long relative_id;

    public String name;
    public String surname;
    public String middlename;
    public String info;

    public Entity_Relative_AddedNeedy(String name, String surname, String middlename, String info, long relative_id){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.info=info;
        this.relative_id=relative_id;
    }

    public long getId() {
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
