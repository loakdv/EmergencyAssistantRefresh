/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:52 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedyTask;

import java.util.List;

@Dao
public interface DaoVolunteerAddedNeedyTask {

    @Query("SELECT * FROM EntityVolunteerAddedNeedyTask")
    List<EntityVolunteerAddedNeedyTask> getAll();

    @Query("SELECT * FROM EntityVolunteerAddedNeedyTask WHERE id=:id")
    EntityVolunteerAddedNeedyTask getById(long id);



    @Query("SELECT * FROM EntityVolunteerAddedNeedyTask WHERE date=:date AND needy_id=:needyId")
    List<EntityVolunteerAddedNeedyTask> getByABC(String date, String needyId);

    @Query("SELECT * FROM EntityVolunteerAddedNeedyTask WHERE date=:date")
    List<EntityVolunteerAddedNeedyTask> getByDate(String date);

    @Query("SELECT * FROM EntityVolunteerAddedNeedyTask WHERE needy_id=:needyId")
    List<EntityVolunteerAddedNeedyTask> getByNeedyId(String needyId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityVolunteerAddedNeedyTask task);

    @Update
    void update(EntityVolunteerAddedNeedyTask task);

    @Delete
    void delete(EntityVolunteerAddedNeedyTask task);
}
