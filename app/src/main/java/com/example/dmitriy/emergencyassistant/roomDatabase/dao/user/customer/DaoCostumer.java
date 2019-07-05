package com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.customer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomer;

import java.util.List;

@Dao
public interface DaoCostumer {

    @Query("SELECT * FROM EntityCustomer")
    List<EntityCustomer> getAll();

    @Query("SELECT * FROM EntityCustomer WHERE id= :id")
    EntityCustomer getById(long id);

    @Query("SELECT * FROM EntityCustomer")
    EntityCustomer getNeedy();


    @Query("UPDATE EntityCustomer SET state_signal=:state")
    void setState(int state);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityCustomer needy);

    @Delete
    void delete(EntityCustomer needy);

    @Update
    void update(EntityCustomer needy);





}
