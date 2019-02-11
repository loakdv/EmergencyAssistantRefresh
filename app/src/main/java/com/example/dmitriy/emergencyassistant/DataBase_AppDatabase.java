package com.example.dmitriy.emergencyassistant;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Entity_Added_PhoneNumbers.class, Entity_Added_Relatives.class,
Entity_Needy.class, Entity_Needy_State.class,Entity_Profile.class,
Entity_Relative.class, Entity_Relative_AddedNeedy.class, Entity_Relative_AddedNeedy_Note.class,
Entity_Relative_AddedNeedy_State.class, Entity_Volunteer_AddedNeedy.class, Entity_Volunteer.class,
Entity_Volunteer_AddedNeedy_Task.class, Entity_Volunteer_Task.class}, version = 1)
public abstract class DataBase_AppDatabase extends RoomDatabase {
}
