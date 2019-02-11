package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Needy_State {

    @Query("SELECT * FROM Entity_Needy_State")
    List<Entity_Needy_State> getAll();

    @Query("SELECT * FROM Entity_Needy_State WHERE id=:id")
    Entity_Needy_State getById(long id);

    @Insert
    void insert(Entity_Needy_State state);

    @Delete
    void delete(Entity_Needy_State state);

    @Update
    void update(Entity_Needy_State state);
}
