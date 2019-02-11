package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Needy.class, parentColumns = "id", childColumns = "needy_id", onDelete = CASCADE))
public class Entity_Added_PhoneNumbers {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public long needy_id;
    public String number;
    public String name;
    public byte[] image;


    public Entity_Added_PhoneNumbers(String name, String number, byte[] image, long needy_id){
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
