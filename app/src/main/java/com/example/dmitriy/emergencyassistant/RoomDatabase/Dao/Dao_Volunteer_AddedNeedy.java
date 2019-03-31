package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;

import java.util.List;

@Dao
public interface Dao_Volunteer_AddedNeedy {

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy")
    List<Entity_Volunteer_AddedNeedy> getAll();

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy WHERE id=:id")
    Entity_Volunteer_AddedNeedy getById(String id);

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy ORDER BY ID DESC")
    Entity_Volunteer_AddedNeedy getLastNeedy();

    @Query("DELETE FROM Entity_Volunteer_AddedNeedy")
    void clearAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Entity_Volunteer_AddedNeedy needy);

    @Delete
    void delete(Entity_Volunteer_AddedNeedy needy);

    @Update
    void update(Entity_Volunteer_AddedNeedy needy);

}
