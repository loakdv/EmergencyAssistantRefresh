package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Relative;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelativeAddedNeedy;

import java.util.List;

@Dao
public interface DaoRelativeAddedNeedy {

    @Query("SELECT * FROM EntityRelativeAddedNeedy")
    List<EntityRelativeAddedNeedy> getAll();

    @Query("SELECT * FROM EntityRelativeAddedNeedy WHERE id=:id")
    EntityRelativeAddedNeedy getById(String id);

    @Query("SELECT * FROM EntityRelativeAddedNeedy ORDER BY ID DESC LIMIT 1")
    EntityRelativeAddedNeedy getLastNeedy();

    @Query("SELECT COUNT(*) FROM EntityRelativeAddedNeedy")
    int getSize();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityRelativeAddedNeedy added_needy);

    @Delete
    void delete(EntityRelativeAddedNeedy added_needy);

    @Update
    void update(EntityRelativeAddedNeedy added_needy);

}
