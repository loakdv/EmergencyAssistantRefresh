package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Relative_AddedNeedy_Note {

    @Query("SELECT * FROM Entity_Relative_AddedNeedy_Note")
    List<Entity_Relative_AddedNeedy_Note> getAll();

    @Query("SELECT * FROM Entity_Relative_AddedNeedy_Note WHERE id=:id")
    Entity_Relative_AddedNeedy_Note getById(long id);

    @Update
    void update(Entity_Relative_AddedNeedy_Note note);

    @Insert
    void insert(Entity_Relative_AddedNeedy_Note note);

    @Delete
    void delete(Entity_Relative_AddedNeedy_Note note);

}
