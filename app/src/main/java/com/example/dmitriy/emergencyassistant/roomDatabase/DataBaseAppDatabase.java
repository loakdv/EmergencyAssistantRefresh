/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/1/19 6:38 PM
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

@Database(entities = {EntityUser.class}, version = 1, exportSchema = false)
public abstract class DataBaseAppDatabase extends RoomDatabase {

    public abstract DaoUser daoUser();
}
