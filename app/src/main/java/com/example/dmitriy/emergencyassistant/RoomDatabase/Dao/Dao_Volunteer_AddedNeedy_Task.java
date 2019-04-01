package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy_Task;

import java.util.List;

@Dao
public interface Dao_Volunteer_AddedNeedy_Task {

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task")
    List<Entity_Volunteer_AddedNeedy_Task> getAll();

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task WHERE id=:id")
    Entity_Volunteer_AddedNeedy_Task getById(long id);

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task WHERE needy_id=:id")
    List<Entity_Volunteer_AddedNeedy_Task> getAllById(long id);

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task WHERE date=:date")
    List<Entity_Volunteer_AddedNeedy_Task> getByDate(String date);

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task WHERE time=:time")
    List<Entity_Volunteer_AddedNeedy_Task> getByTime(String time);

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy_Task WHERE time=:date AND isConfirmed=:conf")
    List<Entity_Volunteer_AddedNeedy_Task> getByDateAndConfirmed(String date, boolean conf);

    @Query("UPDATE Entity_Volunteer_AddedNeedy_Task SET isConfirmed=:conf WHERE id=:id")
    void updateConfirmed(boolean conf, long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Entity_Volunteer_AddedNeedy_Task task);

    @Update
    void update(Entity_Volunteer_AddedNeedy_Task task);

    @Delete
    void delete(Entity_Volunteer_AddedNeedy_Task task);
}
