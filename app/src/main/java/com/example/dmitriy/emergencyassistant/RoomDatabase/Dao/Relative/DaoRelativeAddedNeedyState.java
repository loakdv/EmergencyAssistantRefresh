package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Relative;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelativeAddedNeedyState;

import java.util.List;

@Dao
public interface DaoRelativeAddedNeedyState {

    @Query("SELECT * FROM EntityRelativeAddedNeedyState")
    List<EntityRelativeAddedNeedyState> getAll();

    @Query("SELECT * FROM EntityRelativeAddedNeedyState WHERE id=:id")
    EntityRelativeAddedNeedyState getById(String id);


    @Insert
    void insert (EntityRelativeAddedNeedyState state);

    @Delete
    void delete(EntityRelativeAddedNeedyState state);

    @Update
    void update(EntityRelativeAddedNeedyState state);

}
