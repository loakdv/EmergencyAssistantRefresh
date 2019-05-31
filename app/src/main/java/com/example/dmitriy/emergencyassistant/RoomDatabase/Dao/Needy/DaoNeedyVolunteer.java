package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyFixedVolunteer;

import java.util.List;

@Dao
public interface DaoNeedyVolunteer {

    @Query("SELECT * FROM EntityNeedyFixedVolunteer")
    List<EntityNeedyFixedVolunteer> getAll();


    @Query("SELECT * FROM EntityNeedyFixedVolunteer WHERE id=:id")
    EntityNeedyFixedVolunteer getById(long id);

    @Query("SELECT * FROM EntityNeedyFixedVolunteer")
    EntityNeedyFixedVolunteer getVolunteer();

    @Insert
    void insert(EntityNeedyFixedVolunteer volunteer);

    @Delete
    void delete(EntityNeedyFixedVolunteer volunteer);

    @Update
    void update(EntityNeedyFixedVolunteer volunteer);

}
