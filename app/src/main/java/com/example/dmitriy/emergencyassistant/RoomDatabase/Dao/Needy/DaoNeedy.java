package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedy;

import java.util.List;

@Dao
public interface DaoNeedy {

    @Query("SELECT * FROM EntityNeedy")
    List<EntityNeedy> getAll();

    @Query("SELECT * FROM EntityNeedy WHERE id= :id")
    EntityNeedy getById(long id);

    @Query("SELECT * FROM EntityNeedy")
    EntityNeedy getNeedy();


    @Query("UPDATE EntityNeedy SET state_signal=:state")
    void setState(int state);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityNeedy needy);

    @Delete
    void delete(EntityNeedy needy);

    @Update
    void update(EntityNeedy needy);





}
