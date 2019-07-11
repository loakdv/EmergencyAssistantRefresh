/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:52 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityVolunteer.class, parentColumns = "id", childColumns = "volunteer_id", onDelete = CASCADE),
        indices = {@Index(value = "volunteer_id", unique = false)})
public class EntityVolunteerTask {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long volunteer_id;

    public String name;
    public String about;

    public EntityVolunteerTask(String name, String about, long volunteer_id){
        this.name=name;
        this.about=about;
        this.volunteer_id=volunteer_id;
    }

    public long getId() {
        return this.id;
    }

    public long getVolunteer_id() {
        return this.volunteer_id;
    }

    public String getName() {
        return this.name;
    }

    public String getAbout() {
        return this.about;
    }
}
