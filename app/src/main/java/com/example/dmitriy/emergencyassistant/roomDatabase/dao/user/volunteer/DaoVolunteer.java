package com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteer;

import java.util.List;

@Dao
public interface DaoVolunteer {

    @Query("SELECT * FROM EntityVolunteer")
    List<EntityVolunteer> getAll();

    @Query("SELECT * FROM EntityVolunteer WHERE id=:id")
    EntityVolunteer getById(long id);

    @Query("SELECT * FROM EntityVolunteer")
    EntityVolunteer get_Volunteer();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityVolunteer volunteer);

    @Update
    void update(EntityVolunteer volunteer);

    @Delete
    void delete(EntityVolunteer volunteer);


}
