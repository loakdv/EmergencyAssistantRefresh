package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Added_Relatives;

import java.util.List;

@Dao
public interface Dao_Added_Relatives {

    @Query("SELECT * FROM Entity_Added_Relatives")
    List<Entity_Added_Relatives> getAll();

    @Query("SELECT * FROM Entity_Added_Relatives WHERE id=:id")
    Entity_Added_Relatives getById(String id);

    @Query("SELECT * FROM Entity_Added_Relatives WHERE doctor= :doctor")
    List<Entity_Added_Relatives> getByDoc(boolean doctor);

    @Update
    void update(Entity_Added_Relatives relative);

    @Delete
    void delete(Entity_Added_Relatives relative);

    @Insert
    void insert(Entity_Added_Relatives relative);


}
