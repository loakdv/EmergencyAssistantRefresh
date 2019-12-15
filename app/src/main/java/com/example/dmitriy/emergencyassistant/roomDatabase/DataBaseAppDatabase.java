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


import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.DaoNumbers;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.DaoTasks;
import com.example.dmitriy.emergencyassistant.roomDatabase.dao.user.DaoUser;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityPhoneNumber;


@Database(entities = {EntityUser.class, EntityPhoneNumber.class}, version = 1, exportSchema = false)
public abstract class DataBaseAppDatabase extends RoomDatabase {

    public abstract DaoUser daoUser();
    public abstract DaoTasks daoTasks();
    public abstract DaoNumbers daoNumbers();
}
