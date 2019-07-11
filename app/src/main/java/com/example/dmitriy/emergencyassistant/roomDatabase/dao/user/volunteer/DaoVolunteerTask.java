/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerTask;

import java.util.List;

@Dao
public interface DaoVolunteerTask {

    @Query("SELECT * FROM EntityVolunteerTask")
    List<EntityVolunteerTask> getAll();

    @Query("SELECT * FROM EntityVolunteerTask WHERE id=:id")
    EntityVolunteerTask getById(long id);

    @Insert
    void insert(EntityVolunteerTask task);

    @Update
    void update(EntityVolunteerTask task);

    @Delete
    void delete(EntityVolunteerTask task);


}
