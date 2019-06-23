package com.example.dmitriy.emergencyassistant.roomDatabase.dao.customer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.customer.EntityCustomerState;

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
