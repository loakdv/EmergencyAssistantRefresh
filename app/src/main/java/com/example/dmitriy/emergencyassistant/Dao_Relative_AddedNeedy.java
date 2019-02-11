package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Relative_AddedNeedy {

    @Query("SELECT * FROM Entity_Relative_AddedNeedy")
    List<Entity_Relative_AddedNeedy> getAll();

    @Query("SELECT * FROM Entity_Relative_AddedNeedy WHERE id=:id")
    Entity_Relative_AddedNeedy getById(long id);

    @Insert
    void insert(Entity_Relative_AddedNeedy added_needy);

    @Delete
    void delete(Entity_Relative_AddedNeedy added_needy);

    @Update
    void update(Entity_Relative_AddedNeedy added_needy);

}
