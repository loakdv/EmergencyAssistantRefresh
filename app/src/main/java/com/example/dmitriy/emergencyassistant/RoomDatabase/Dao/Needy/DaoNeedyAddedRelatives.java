package com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyAddedRelatives;

import java.util.List;

@Dao
public interface DaoNeedyAddedRelatives {

    @Query("SELECT * FROM EntityNeedyAddedRelatives")
    List<EntityNeedyAddedRelatives> getAll();

    @Query("SELECT * FROM EntityNeedyAddedRelatives WHERE id=:id")
    EntityNeedyAddedRelatives getById(String id);

    @Query("SELECT * FROM EntityNeedyAddedRelatives WHERE doctor= :doctor")
    List<EntityNeedyAddedRelatives> getByDoc(boolean doctor);

    @Update
    void update(EntityNeedyAddedRelatives relative);

    @Delete
    void delete(EntityNeedyAddedRelatives relative);

    @Insert
    void insert(EntityNeedyAddedRelatives relative);


}
