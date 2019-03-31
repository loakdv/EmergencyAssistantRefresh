package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.Entity_Relative;

import java.util.List;

@Dao
public interface Dao_Relative {

    @Query("SELECT * FROM Entity_Relative")
    List<Entity_Relative> getAll();

    @Query("SELECT * FROM Entity_Relative WHERE id=:id")
    Entity_Relative getById(long id);

    @Query("SELECT * FROM Entity_Relative")
    Entity_Relative getRelative();

    @Query("SELECT * FROM Entity_Relative WHERE profile_id = :id")
    Entity_Relative getByProfileId(String id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Entity_Relative relative);

    @Delete
    void delete(Entity_Relative relative);

    @Update
    void update(Entity_Relative relative);

}
