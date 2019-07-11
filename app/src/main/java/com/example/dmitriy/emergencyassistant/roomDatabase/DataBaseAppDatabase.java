/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:52 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.customer.DaoCostumer;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.customer.DaoCostumerAddedPhoneNumbers;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.customer.DaoCostumerConnectedVolunteer;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.customer.DaoCostumerState;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.DaoUser;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer.DaoVolunteerAddedNeedyNote;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer.DaoVolunteerAddedNeedyState;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer.DaoVolunteer;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer.DaoVolunteerAddedNeedyTask;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer.DaoVolunteerAddedNeedy;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.volunteer.DaoVolunteerTask;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomer;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerAddedPhoneNumbers;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerConnectedVolunteer;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerState;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedyState;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedyNote;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteer;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedyTask;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerTask;

@Database(entities = {EntityCustomerAddedPhoneNumbers.class,
EntityCustomer.class, EntityCustomerState.class, EntityUser.class,
 EntityVolunteerAddedNeedyNote.class,
EntityVolunteerAddedNeedyState.class, EntityVolunteerAddedNeedy.class, EntityVolunteer.class,
EntityVolunteerAddedNeedyTask.class, EntityVolunteerTask.class, EntityCustomerConnectedVolunteer.class}, version = 1, exportSchema = false)
public abstract class DataBaseAppDatabase extends RoomDatabase {

    public abstract DaoCostumerAddedPhoneNumbers dao_added_phoneNumbers();
    public abstract DaoCostumer dao_needy();
    public abstract DaoCostumerState dao_needy_state();
    public abstract DaoUser dao_user();
    public abstract DaoVolunteerAddedNeedyNote dao_volunteer_addedNeedy_note();
    public abstract DaoVolunteerAddedNeedyState dao_volunteer_addedNeedy_state();
    public abstract DaoVolunteer dao_volunteer();
    public abstract DaoVolunteerAddedNeedy dao_volunteer_addedNeedy();
    public abstract DaoVolunteerAddedNeedyTask dao_volunteer_addedNeedy_task();
    public abstract DaoVolunteerTask dao_volunteer_task();
    public abstract DaoCostumerConnectedVolunteer dao_needy_volunteer();


}
