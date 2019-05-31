package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Volunteer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.EntityVolunteerTask;

import java.util.List;

@Dao
public interface DaoVolunteerTask {

    @Query("SELECT * FROM EntityVolunteerTask")
    List<EntityVolunteerTask> getAll();

    @Query("SELECT * FROM EntityVolunteerTask WHERE id=:id")
    EntityVolunteerTask getById(long id);

    @Insert
    void insert(EntityVolunteerTask task);

    @Update
    void update(EntityVolunteerTask task);

    @Delete
    void delete(EntityVolunteerTask task);


}
