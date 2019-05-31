package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyAddedPhoneNumbers;

import java.util.List;

@Dao
public interface DaoNeedyAddedPhoneNumbers {

    @Query("SELECT * FROM EntityNeedyAddedPhoneNumbers")
    List<EntityNeedyAddedPhoneNumbers> getAll();

    @Query("SELECT * FROM EntityNeedyAddedPhoneNumbers WHERE id=:id")
    EntityNeedyAddedPhoneNumbers getById(long id);

    @Insert
    void insert(EntityNeedyAddedPhoneNumbers number);

    @Delete
    void delete(EntityNeedyAddedPhoneNumbers number);

    @Update
    void update(EntityNeedyAddedPhoneNumbers number);

}
