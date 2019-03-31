package com.example.dmitriy.emergencyassistant.RoomDatabase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Added_PhoneNumbers;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Added_Relatives;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Needy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Needy_State;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Needy_Volunteer;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Profile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Relative;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Relative_AddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Relative_AddedNeedy_Note;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Relative_AddedNeedy_State;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Volunteer;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Volunteer_AddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Volunteer_AddedNeedy_Task;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Dao_Volunteer_Task;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Added_PhoneNumbers;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Added_Relatives;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy_State;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy_Volunteer;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.Entity_Relative;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.Entity_Relative_AddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.Entity_Relative_AddedNeedy_Note;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.Entity_Relative_AddedNeedy_State;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy_Task;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_Task;

@Database(entities = {Entity_Added_PhoneNumbers.class, Entity_Added_Relatives.class,
Entity_Needy.class, Entity_Needy_State.class, Entity_Profile.class,
Entity_Relative.class, Entity_Relative_AddedNeedy.class, Entity_Relative_AddedNeedy_Note.class,
Entity_Relative_AddedNeedy_State.class, Entity_Volunteer_AddedNeedy.class, Entity_Volunteer.class,
Entity_Volunteer_AddedNeedy_Task.class, Entity_Volunteer_Task.class, Entity_Needy_Volunteer.class}, version = 1, exportSchema = false)
public abstract class DataBase_AppDatabase extends RoomDatabase {

    public abstract Dao_Added_PhoneNumbers dao_added_phoneNumbers();
    public abstract Dao_Added_Relatives dao_added_relatives();
    public abstract Dao_Needy dao_needy();
    public abstract Dao_Needy_State dao_needy_state();
    public abstract Dao_Profile dao_profile();
    public abstract Dao_Relative dao_relative();
    public abstract Dao_Relative_AddedNeedy dao_relative_addedNeedy();
    public abstract Dao_Relative_AddedNeedy_Note dao_relative_addedNeedy_note();
    public abstract Dao_Relative_AddedNeedy_State dao_relative_addedNeedy_state();
    public abstract Dao_Volunteer dao_volunteer();
    public abstract Dao_Volunteer_AddedNeedy dao_volunteer_addedNeedy();
    public abstract Dao_Volunteer_AddedNeedy_Task dao_volunteer_addedNeedy_task();
    public abstract Dao_Volunteer_Task dao_volunteer_task();
    public abstract Dao_Needy_Volunteer dao_needy_volunteer();


}
