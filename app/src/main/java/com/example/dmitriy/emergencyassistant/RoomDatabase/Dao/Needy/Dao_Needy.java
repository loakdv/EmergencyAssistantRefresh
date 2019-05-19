package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy;

import java.util.List;

@Dao
public interface Dao_Needy {

    @Query("SELECT * FROM Entity_Needy")
    List<Entity_Needy> getAll();

    @Query("SELECT * FROM Entity_Needy WHERE id= :id")
    Entity_Needy getById(long id);

    @Query("SELECT * FROM Entity_Needy")
    Entity_Needy getNeedy();


    @Query("UPDATE Entity_Needy SET state_signal=:state")
    void setState(int state);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Entity_Needy needy);

    @Delete
    void delete(Entity_Needy needy);

    @Update
    void update(Entity_Needy needy);





}
