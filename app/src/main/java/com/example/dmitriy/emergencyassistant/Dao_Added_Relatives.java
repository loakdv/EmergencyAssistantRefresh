package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Added_Relatives {

    @Query("SELECT * FROM Entity_Added_Relatives")
    List<Entity_Added_Relatives> getAll();

    @Query("SELECT * FROM Entity_Added_Relatives WHERE id=:id")
    Entity_Added_Relatives getById(long id);

    @Update
    void update(Entity_Added_Relatives relative);

    @Delete
    void delete(Entity_Added_Relatives relative);

    @Insert
    void insert(Entity_Added_Relatives relative);


}
