package com.example.dmitriy.emergencyassistant.roomDatabase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.dmitriy.emergencyassistant.roomDatabase.dao.customer.DaoCostumer;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.customer.DaoCostumerAddedPhoneNumbers;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.customer.DaoCostumerConnectedVolunteer;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.customer.DaoCostumerState;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.profile.DaoUser;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.volunteer.DaoVolunteerAddedNeedyNote;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.volunteer.DaoVolunteerAddedNeedyState;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.volunteer.DaoVolunteer;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.volunteer.DaoVolunteerAddedNeedyTask;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.volunteer.DaoVolunteerAddedNeedy;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.volunteer.DaoVolunteerTask;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.customer.EntityCustomer;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.customer.EntityCustomerAddedPhoneNumbers;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.customer.EntityCustomerConnectedVolunteer;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.customer.EntityCustomerState;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.profile.EntityProfile;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer.EntityVolunteerAddedNeedyState;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer.EntityVolunteerAddedNeedyNote;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer.EntityVolunteer;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer.EntityVolunteerAddedNeedyTask;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer.EntityVolunteerAddedNeedy;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer.EntityVolunteerTask;

@Database(entities = {EntityCustomerAddedPhoneNumbers.class,
EntityCustomer.class, EntityCustomerState.class, EntityProfile.class,
 EntityVolunteerAddedNeedyNote.class,
EntityVolunteerAddedNeedyState.class, EntityVolunteerAddedNeedy.class, EntityVolunteer.class,
EntityVolunteerAddedNeedyTask.class, EntityVolunteerTask.class, EntityCustomerConnectedVolunteer.class}, version = 1, exportSchema = false)
public abstract class DataBaseAppDatabase extends RoomDatabase {

    public abstract DaoCostumerAddedPhoneNumbers dao_added_phoneNumbers();
    public abstract DaoCostumer dao_needy();
    public abstract DaoCostumerState dao_needy_state();
    public abstract DaoUser dao_profile();
    public abstract DaoVolunteerAddedNeedyNote dao_volunteer_addedNeedy_note();
    public abstract DaoVolunteerAddedNeedyState dao_volunteer_addedNeedy_state();
    public abstract DaoVolunteer dao_volunteer();
    public abstract DaoVolunteerAddedNeedy dao_volunteer_addedNeedy();
    public abstract DaoVolunteerAddedNeedyTask dao_volunteer_addedNeedy_task();
    public abstract DaoVolunteerTask dao_volunteer_task();
    public abstract DaoCostumerConnectedVolunteer dao_needy_volunteer();


}
