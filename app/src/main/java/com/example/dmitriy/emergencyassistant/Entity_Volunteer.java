package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Profile.class, parentColumns = "id", childColumns = "profile_id", onDelete = CASCADE),
        indices = {@Index(value = "profile_id", unique = false)})
public class Entity_Volunteer {

    @PrimaryKey
    public long id;

    public String profile_id;
    public String organization;

    public Entity_Volunteer(String organization, String profile_id){
        this.profile_id=profile_id;
        this.organization=organization;
    }

    public long getId() {
        return this.id;
    }

    public String getProfile_id() {
        return this.profile_id;
    }

    public String getOrganization() {
        return this.organization;
    }
}
