package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;

import java.util.List;

@Dao
public interface Dao_Profile {

    @Query("SELECT * FROM Entity_Profile")
    List<Entity_Profile> getAll();

    @Query("SELECT * FROM Entity_Profile WHERE id=:id")
    Entity_Profile getById(String id);

    @Query("SELECT * FROM Entity_Profile")
    Entity_Profile getProfile();



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Entity_Profile profile);

    @Delete
    void delete(Entity_Profile profile);

    @Update
    void update(Entity_Profile profile);
}
