package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer;

import java.util.List;

@Dao
public interface Dao_Volunteer {

    @Query("SELECT * FROM Entity_Volunteer")
    List<Entity_Volunteer> getAll();

    @Query("SELECT * FROM Entity_Volunteer WHERE id=:id")
    Entity_Volunteer getById(long id);

    @Query("SELECT * FROM Entity_Volunteer")
    Entity_Volunteer get_Volunteer();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Entity_Volunteer volunteer);

    @Update
    void update(Entity_Volunteer volunteer);

    @Delete
    void delete(Entity_Volunteer volunteer);


}
