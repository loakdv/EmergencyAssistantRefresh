package com.example.dmitriy.emergencyassistant.roomDatabase.dao.customer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.customer.EntityCustomerConnectedVolunteer;

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
