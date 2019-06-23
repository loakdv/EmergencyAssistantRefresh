package com.example.dmitriy.emergencyassistant.roomDatabase.dao.volunteer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer.EntityVolunteerAddedNeedyState;

import java.util.List;

@Dao
public interface DaoVolunteerAddedNeedyState {

    @Query("SELECT * FROM EntityVolunteerAddedNeedyState")
    List<EntityVolunteerAddedNeedyState> getAll();

    @Query("SELECT * FROM EntityVolunteerAddedNeedyState WHERE id=:id")
    EntityVolunteerAddedNeedyState getById(String id);


    @Insert
    void insert (EntityVolunteerAddedNeedyState state);

    @Delete
    void delete(EntityVolunteerAddedNeedyState state);

    @Update
    void update(EntityVolunteerAddedNeedyState state);

}
