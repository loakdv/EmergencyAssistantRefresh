package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Added_PhoneNumbers;

import java.util.List;

@Dao
public interface Dao_Added_PhoneNumbers {

    @Query("SELECT * FROM Entity_Added_PhoneNumbers")
    List<Entity_Added_PhoneNumbers> getAll();

    @Query("SELECT * FROM Entity_Added_PhoneNumbers WHERE id=:id")
    Entity_Added_PhoneNumbers getById(long id);

    @Insert
    void insert(Entity_Added_PhoneNumbers number);

    @Delete
    void delete(Entity_Added_PhoneNumbers number);

    @Update
    void update(Entity_Added_PhoneNumbers number);

}
