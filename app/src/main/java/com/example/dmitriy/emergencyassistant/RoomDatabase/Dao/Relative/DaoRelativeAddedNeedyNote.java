package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Relative;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelativeAddedNeedyNote;

import java.util.List;

@Dao
public interface DaoRelativeAddedNeedyNote {

    @Query("SELECT * FROM EntityRelativeAddedNeedyNote")
    List<EntityRelativeAddedNeedyNote> getAll();

    @Query("SELECT * FROM EntityRelativeAddedNeedyNote WHERE id=:id")
    EntityRelativeAddedNeedyNote getById(String id);

    @Query("SELECT * FROM EntityRelativeAddedNeedyNote WHERE needy_id=:needy_id")
    List<EntityRelativeAddedNeedyNote> getByNeedyId(String needy_id);

    @Update
    void update(EntityRelativeAddedNeedyNote note);

    @Insert
    void insert(EntityRelativeAddedNeedyNote note);

    @Delete
    void delete(EntityRelativeAddedNeedyNote note);

}
