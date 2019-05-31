package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Relative;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelative;

import java.util.List;

@Dao
public interface DaoRelative {

    @Query("SELECT * FROM EntityRelative")
    List<EntityRelative> getAll();

    @Query("SELECT * FROM EntityRelative WHERE id=:id")
    EntityRelative getById(long id);

    @Query("SELECT * FROM EntityRelative")
    EntityRelative getRelative();



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityRelative relative);

    @Delete
    void delete(EntityRelative relative);

    @Update
    void update(EntityRelative relative);

}
