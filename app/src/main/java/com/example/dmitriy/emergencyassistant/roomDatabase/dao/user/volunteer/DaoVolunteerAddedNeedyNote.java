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

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedyNote;

import java.util.List;

@Dao
public interface DaoVolunteerAddedNeedyNote {

    @Query("SELECT * FROM EntityVolunteerAddedNeedyNote")
    List<EntityVolunteerAddedNeedyNote> getAll();

    @Query("SELECT * FROM EntityVolunteerAddedNeedyNote WHERE id=:id")
    EntityVolunteerAddedNeedyNote getById(String id);

    @Query("SELECT * FROM EntityVolunteerAddedNeedyNote WHERE needy_id=:needy_id")
    List<EntityVolunteerAddedNeedyNote> getByNeedyId(String needy_id);

    @Update
    void update(EntityVolunteerAddedNeedyNote note);

    @Insert
    void insert(EntityVolunteerAddedNeedyNote note);

    @Delete
    void delete(EntityVolunteerAddedNeedyNote note);

}
