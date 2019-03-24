package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Relative_AddedNeedy_State {

    @Query("SELECT * FROM Entity_Relative_AddedNeedy_State")
    List<Entity_Relative_AddedNeedy_State> getAll();

    @Query("SELECT * FROM Entity_Relative_AddedNeedy_State WHERE id=:id")
    Entity_Relative_AddedNeedy_State getById(String id);

    @Query("SELECT s9 FROM Entity_Relative_AddedNeedy_State WHERE needy_id=:needy_id")
    int getS9(String needy_id);

    @Query("SELECT s12 FROM Entity_Relative_AddedNeedy_State WHERE needy_id=:needy_id")
    int getS12(String needy_id);

    @Query("SELECT s15 FROM Entity_Relative_AddedNeedy_State WHERE needy_id=:needy_id")
    int getS15(String needy_id);

    @Query("SELECT s18 FROM Entity_Relative_AddedNeedy_State WHERE needy_id=:needy_id")
    int getS18(String needy_id);

    @Query("SELECT s21 FROM Entity_Relative_AddedNeedy_State WHERE needy_id=:needy_id")
    int getS21(String needy_id);

    @Insert
    void insert (Entity_Relative_AddedNeedy_State state);

    @Delete
    void delete(Entity_Relative_AddedNeedy_State state);

    @Update
    void update(Entity_Relative_AddedNeedy_State state);

}
