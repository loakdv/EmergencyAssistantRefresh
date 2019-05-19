package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Relative;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.Entity_Relative_AddedNeedy;

import java.util.List;

@Dao
public interface Dao_Relative_AddedNeedy {

    @Query("SELECT * FROM Entity_Relative_AddedNeedy")
    List<Entity_Relative_AddedNeedy> getAll();

    @Query("SELECT * FROM Entity_Relative_AddedNeedy WHERE id=:id")
    Entity_Relative_AddedNeedy getById(String id);

    @Query("SELECT * FROM Entity_Relative_AddedNeedy ORDER BY ID DESC LIMIT 1")
    Entity_Relative_AddedNeedy getLastNeedy();

    @Query("SELECT COUNT(*) FROM Entity_Relative_AddedNeedy")
    int getSize();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Entity_Relative_AddedNeedy added_needy);

    @Delete
    void delete(Entity_Relative_AddedNeedy added_needy);

    @Update
    void update(Entity_Relative_AddedNeedy added_needy);

}
