package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Relative_AddedNeedy_State {

    @Query("SELECT * FROM Entity_Relative_AddedNeedy_State")
    List<Entity_Relative_AddedNeedy_State> getAll();

    @Query("SELECT * FROM Entity_Relative_AddedNeedy_State WHERE id=:id")
    Entity_Relative_AddedNeedy_State getById(long id);

    @Insert
    void insert (Entity_Relative_AddedNeedy_State state);

    @Delete
    void delete(Entity_Relative_AddedNeedy_State state);

    @Update
    void update(Entity_Relative_AddedNeedy_State state);

}
