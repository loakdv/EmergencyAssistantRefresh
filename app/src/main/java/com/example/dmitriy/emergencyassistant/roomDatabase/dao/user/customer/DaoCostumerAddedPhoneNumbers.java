package com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.customer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerAddedPhoneNumbers;

import java.util.List;

@Dao
public interface DaoCostumerAddedPhoneNumbers {

    @Query("SELECT * FROM EntityCustomerAddedPhoneNumbers")
    List<EntityCustomerAddedPhoneNumbers> getAll();

    @Query("SELECT * FROM EntityCustomerAddedPhoneNumbers WHERE id=:id")
    EntityCustomerAddedPhoneNumbers getById(long id);

    @Insert
    void insert(EntityCustomerAddedPhoneNumbers number);

    @Delete
    void delete(EntityCustomerAddedPhoneNumbers number);

    @Update
    void update(EntityCustomerAddedPhoneNumbers number);

}
