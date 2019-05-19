package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy_Volunteer;

import java.util.List;

@Dao
public interface Dao_Needy_Volunteer {

    @Query("SELECT * FROM Entity_Needy_Volunteer")
    List<Entity_Needy_Volunteer> getAll();


    @Query("SELECT * FROM Entity_Needy_Volunteer WHERE id=:id")
    Entity_Needy_Volunteer getById(long id);

    @Query("SELECT * FROM Entity_Needy_Volunteer")
    Entity_Needy_Volunteer getVolunteer();

    @Insert
    void insert(Entity_Needy_Volunteer volunteer);

    @Delete
    void delete(Entity_Needy_Volunteer volunteer);

    @Update
    void update(Entity_Needy_Volunteer volunteer);

}
