package com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityNeedy.class, parentColumns = "id", childColumns = "needy_id", onDelete = CASCADE),
indices = {@Index(value = "needy_id", unique = false)})
public class EntityNeedyAddedPhoneNumbers {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public long needy_id;
    public String number;
    public String name;
    public byte[] image;


    public EntityNeedyAddedPhoneNumbers(String name, String number, byte[] image, long needy_id){
        this.needy_id=needy_id;
        this.number=number;
        this.name=name;
        this.image=image;
    }

    public long getId() {
        return this.id;
    }

    public long getNeedy_id() {
        return this.needy_id;
    }

    public String getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public byte[] getImage() {
        return this.image;
    }
}
