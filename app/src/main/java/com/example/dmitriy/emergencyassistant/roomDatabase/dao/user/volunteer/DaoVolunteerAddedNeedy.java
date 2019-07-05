package com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;

import java.util.List;

@Dao
public interface DaoVolunteerAddedNeedy {

    @Query("SELECT * FROM EntityVolunteerAddedNeedy")
    List<EntityVolunteerAddedNeedy> getAll();


    @Query("SELECT * FROM EntityVolunteerAddedNeedy WHERE needyId=:needyId AND date=:date")
    List<EntityVolunteerAddedNeedy> getListBydateID(String needyId, String date);

    @Query("SELECT * FROM EntityVolunteerAddedNeedy WHERE date=:date")
    List<EntityVolunteerAddedNeedy> getListByDate(String date);

    @Query("SELECT * FROM EntityVolunteerAddedNeedy WHERE needyId =:id")
    EntityVolunteerAddedNeedy getById(String id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityVolunteerAddedNeedy needy);

    @Delete
    void delete(EntityVolunteerAddedNeedy needy);

    @Update
    void update(EntityVolunteerAddedNeedy needy);

}
