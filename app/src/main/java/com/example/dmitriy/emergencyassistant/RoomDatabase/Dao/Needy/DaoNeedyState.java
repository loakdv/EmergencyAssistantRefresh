package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyState;

import java.util.List;

@Dao
public interface DaoNeedyState {

    @Query("SELECT * FROM EntityNeedyState")
    List<EntityNeedyState> getAll();

    @Query("SELECT * FROM EntityNeedyState WHERE id=:id")
    EntityNeedyState getById(long id);

    @Query("DELETE FROM EntityNeedyState")
    void clearTable();

    @Insert
    void insert(EntityNeedyState state);

    @Delete
    void delete(EntityNeedyState state);

    @Update
    void update(EntityNeedyState state);
}
