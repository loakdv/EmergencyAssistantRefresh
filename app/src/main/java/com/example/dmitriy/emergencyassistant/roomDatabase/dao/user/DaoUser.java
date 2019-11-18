/*
 *
 *  Created by Dmitry Garmyshev on 8/18/19 10:33 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/17/19 10:58 AM
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
    List<EntityUser> getAllUsers();

    @Query("SELECT * FROM EntityUser WHERE id=:id")
    EntityUser getById(Long id);

    @Query("SELECT * FROM EntityUser WHERE nickname=:nickname")
    EntityUser getByNickname(String nickname);




    @Insert()
    void insert(EntityUser profile);

    @Delete
    void delete(EntityUser profile);

    @Update
    void update(EntityUser profile);
}
