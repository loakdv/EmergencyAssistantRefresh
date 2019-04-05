package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;

import java.util.List;

@Dao
public interface Dao_Volunteer_AddedNeedy {

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy")
    List<Entity_Volunteer_AddedNeedy> getAll();

    /*
    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy WHERE id=:id")
    Entity_Volunteer_AddedNeedy getById(String id);

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy ORDER BY ID DESC")
    Entity_Volunteer_AddedNeedy getLastNeedy();
    */

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy WHERE needyId=:needyId AND date=:date")
    List<Entity_Volunteer_AddedNeedy> getListBydateID(String needyId, String date);

    @Query("DELETE FROM Entity_Volunteer_AddedNeedy")
    void clearAll();

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy WHERE date=:date AND isConfirmed=:conf AND needyId=:needyId")
    Entity_Volunteer_AddedNeedy getAdded(String date, boolean conf, String needyId);

    @Query("SELECT * FROM Entity_Volunteer_AddedNeedy WHERE date=:date AND isConfirmed=:conf AND needyId=:needyId")
    List<Entity_Volunteer_AddedNeedy> getListAdded(String date, boolean conf, String needyId);


    @Query("UPDATE Entity_Volunteer_AddedNeedy SET isConfirmed=:conf WHERE needyId=:id AND date=:date")
    void setConfirmed(boolean conf, String id, String date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Entity_Volunteer_AddedNeedy needy);

    @Delete
    void delete(Entity_Volunteer_AddedNeedy needy);

    @Update
    void update(Entity_Volunteer_AddedNeedy needy);

}
