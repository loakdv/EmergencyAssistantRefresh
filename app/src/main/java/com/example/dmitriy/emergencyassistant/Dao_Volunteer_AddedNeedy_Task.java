package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Volunteer_AddedNeedy_Task {

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task")
    List<Entity_Volunteer_AddedNeedy_Task> getAll();

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task WHERE id=:id")
    Entity_Volunteer_AddedNeedy_Task getById(long id);

    @Insert
    void insert(Entity_Volunteer_AddedNeedy_Task task);

    @Update
    void update(Entity_Volunteer_AddedNeedy_Task task);

    @Delete
    void delete(Entity_Volunteer_AddedNeedy_Task task);
}
