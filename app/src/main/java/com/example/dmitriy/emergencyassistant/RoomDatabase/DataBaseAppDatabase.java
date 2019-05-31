package com.example.dmitriy.emergencyassistant.RoomDatabase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy.DaoNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy.DaoNeedyAddedPhoneNumbers;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy.DaoNeedyAddedRelatives;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy.DaoNeedyState;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Needy.DaoNeedyVolunteer;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Profile.DaoProfile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Relative.DaoRelative;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Relative.DaoRelativeAddedNeedyNote;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Relative.DaoRelativeAddedNeedyState;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Relative.DaoRelativeAddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Volunteer.DaoVolunteer;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Volunteer.DaoVolunteerAddedNeedyTask;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Volunteer.DaoVolunteerAddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Dao.Volunteer.DaoVolunteerTask;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyAddedPhoneNumbers;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyFixedVolunteer;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyAddedRelatives;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyState;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.EntityProfile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelative;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelativeAddedNeedyNote;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelativeAddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelativeAddedNeedyState;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.EntityVolunteer;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.EntityVolunteerAddedNeedyTask;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.EntityVolunteerAddedNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.EntityVolunteerTask;

@Database(entities = {EntityNeedyAddedPhoneNumbers.class, EntityNeedyAddedRelatives.class,
EntityNeedy.class, EntityNeedyState.class, EntityProfile.class,
EntityRelative.class, EntityRelativeAddedNeedy.class, EntityRelativeAddedNeedyNote.class,
EntityRelativeAddedNeedyState.class, EntityVolunteerAddedNeedy.class, EntityVolunteer.class,
EntityVolunteerAddedNeedyTask.class, EntityVolunteerTask.class, EntityNeedyFixedVolunteer.class}, version = 1, exportSchema = false)
public abstract class DataBaseAppDatabase extends RoomDatabase {

    public abstract DaoNeedyAddedPhoneNumbers dao_added_phoneNumbers();
    public abstract DaoNeedyAddedRelatives dao_added_relatives();
    public abstract DaoNeedy dao_needy();
    public abstract DaoNeedyState dao_needy_state();
    public abstract DaoProfile dao_profile();
    public abstract DaoRelative dao_relative();
    public abstract DaoRelativeAddedNeedy dao_relative_addedNeedy();
    public abstract DaoRelativeAddedNeedyNote dao_relative_addedNeedy_note();
    public abstract DaoRelativeAddedNeedyState dao_relative_addedNeedy_state();
    public abstract DaoVolunteer dao_volunteer();
    public abstract DaoVolunteerAddedNeedy dao_volunteer_addedNeedy();
    public abstract DaoVolunteerAddedNeedyTask dao_volunteer_addedNeedy_task();
    public abstract DaoVolunteerTask dao_volunteer_task();
    public abstract DaoNeedyVolunteer dao_needy_volunteer();


}
