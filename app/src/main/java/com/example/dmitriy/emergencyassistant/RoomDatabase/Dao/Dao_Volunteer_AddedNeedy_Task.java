package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Volunteer_AddedNeedy_Task;

import java.util.List;

@Dao
public interface Dao_Volunteer_AddedNeedy_Task {

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task")
    List<Entity_Volunteer_AddedNeedy_Task> getAll();

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task WHERE id=:id")
    Entity_Volunteer_AddedNeedy_Task getById(long id);

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task WHERE needy_id=:id")
    List<Entity_Volunteer_AddedNeedy_Task> getAllById(long id);

    @Insert
    void insert(Entity_Volunteer_AddedNeedy_Task task);

    @Update
    void update(Entity_Volunteer_AddedNeedy_Task task);

    @Delete
    void delete(Entity_Volunteer_AddedNeedy_Task task);
}
