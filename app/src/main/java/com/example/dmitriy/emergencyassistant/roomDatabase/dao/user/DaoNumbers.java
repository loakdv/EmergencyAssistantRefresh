/*
 *
 *  Created by Dmitry Garmyshev on 15.12.19 11:37
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 15.12.19 11:37
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.dao.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityPhoneNumber;

import java.util.List;

@Dao
public interface DaoNumbers {

    @Query("SELECT * FROM EntityPhoneNumber")
    List<EntityPhoneNumber> getAllUsers();

    @Query("SELECT * FROM EntityPhoneNumber WHERE id=:id")
    EntityPhoneNumber getById(Long id);

    @Query("SELECT * FROM EntityPhoneNumber WHERE userId=:id")
    List<EntityPhoneNumber> getByUserId(Long id);

    @Query("SELECT * FROM EntityPhoneNumber WHERE name=:name")
    EntityPhoneNumber getByNickname(String name);




    @Insert()
    void insert(EntityPhoneNumber number);

    @Delete
    void delete(EntityPhoneNumber number);

    @Update
    void update(EntityPhoneNumber profile);
}
