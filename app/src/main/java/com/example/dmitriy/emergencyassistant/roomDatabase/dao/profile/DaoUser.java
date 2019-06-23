package com.example.dmitriy.emergencyassistant.roomDatabase.dao.profile;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.profile.EntityProfile;

import java.util.List;

@Dao
public interface DaoUser {

    @Query("SELECT * FROM EntityProfile")
    List<EntityProfile> getAll();

    @Query("SELECT * FROM EntityProfile WHERE id=:id")
    EntityProfile getById(String id);

    @Query("SELECT * FROM EntityProfile")
    EntityProfile getProfile();



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityProfile profile);

    @Delete
    void delete(EntityProfile profile);

    @Update
    void update(EntityProfile profile);
}
