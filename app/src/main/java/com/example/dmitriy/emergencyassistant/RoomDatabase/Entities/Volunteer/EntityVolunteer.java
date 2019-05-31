package com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.EntityProfile;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityProfile.class, parentColumns = "id", childColumns = "profile_id", onDelete = CASCADE),
        indices = {@Index(value = "profile_id", unique = false)})
public class EntityVolunteer {

    @PrimaryKey
    public long id;

    public String profile_id;
    public String organization;

    public EntityVolunteer(String organization, String profile_id){
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
