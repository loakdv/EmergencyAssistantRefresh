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

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerConnectedVolunteer;

import java.util.List;

@Dao
public interface DaoCostumerConnectedVolunteer {

    @Query("SELECT * FROM EntityCustomerConnectedVolunteer")
    List<EntityCustomerConnectedVolunteer> getAll();


    @Query("SELECT * FROM EntityCustomerConnectedVolunteer WHERE id=:id")
    EntityCustomerConnectedVolunteer getById(long id);

    @Query("SELECT * FROM EntityCustomerConnectedVolunteer")
    EntityCustomerConnectedVolunteer getVolunteer();

    @Insert
    void insert(EntityCustomerConnectedVolunteer volunteer);

    @Delete
    void delete(EntityCustomerConnectedVolunteer volunteer);

    @Update
    void update(EntityCustomerConnectedVolunteer volunteer);

}
