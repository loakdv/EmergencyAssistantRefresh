/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.customer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerState;

import java.util.List;

@Dao
public interface DaoCostumerState {

    @Query("SELECT * FROM EntityCustomerState")
    List<EntityCustomerState> getAll();

    @Query("SELECT * FROM EntityCustomerState WHERE id=:id")
    EntityCustomerState getById(long id);

    @Query("DELETE FROM EntityCustomerState")
    void clearTable();

    @Insert
    void insert(EntityCustomerState state);

    @Delete
    void delete(EntityCustomerState state);

    @Update
    void update(EntityCustomerState state);
}
