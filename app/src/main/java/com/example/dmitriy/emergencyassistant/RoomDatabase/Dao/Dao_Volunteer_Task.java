package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_Task;

import java.util.List;

@Dao
public interface Dao_Volunteer_Task {

    @Query("SELECT * FROM Entity_Volunteer_Task")
    List<Entity_Volunteer_Task> getAll();

    @Query("SELECT * FROM Entity_Volunteer_Task WHERE id=:id")
    Entity_Volunteer_Task getById(long id);

    @Insert
    void insert(Entity_Volunteer_Task task);

    @Update
    void update(Entity_Volunteer_Task task);

    @Delete
    void delete(Entity_Volunteer_Task task);


}