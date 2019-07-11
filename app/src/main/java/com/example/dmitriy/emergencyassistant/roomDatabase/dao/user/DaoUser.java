/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:52 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.dao.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;

import java.util.List;

@Dao
public interface DaoUser {

    @Query("SELECT * FROM EntityUser")
    List<EntityUser> getAll();

    @Query("SELECT * FROM EntityUser WHERE id=:id")
    EntityUser getById(String id);

    @Query("SELECT * FROM EntityUser")
    EntityUser getProfile();



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityUser profile);

    @Delete
    void delete(EntityUser profile);

    @Update
    void update(EntityUser profile);
}
