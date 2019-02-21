package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Profile {

    @Query("SELECT * FROM Entity_Profile")
    List<Entity_Profile> getAll();

    @Query("SELECT * FROM Entity_Profile WHERE id=:id")
    Entity_Profile getById(long id);

    @Query("SELECT * FROM Entity_Profile")
    Entity_Profile getProfile();



    @Insert
    void insert(Entity_Profile profile);

    @Delete
    void delete(Entity_Profile profile);

    @Update
    void update(Entity_Profile profile);
}
