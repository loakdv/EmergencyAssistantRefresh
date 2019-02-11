package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Volunteer_AddedNeedy {

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy")
    List<Entity_Volunteer_AddedNeedy> getAll();

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy WHERE id=:id")
    Entity_Volunteer_AddedNeedy getById(long id);

    @Insert
    void insert(Entity_Volunteer_AddedNeedy needy);

    @Delete
    void delete(Entity_Volunteer_AddedNeedy needy);

    @Update
    void update(Entity_Volunteer_AddedNeedy needy);

}
