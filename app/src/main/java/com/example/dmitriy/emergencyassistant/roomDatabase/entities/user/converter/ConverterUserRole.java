/*
 *
 *  Created by Dmitry Garmyshev on 8/18/19 10:33 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/17/19 11:43 AM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.converter;

import android.arch.persistence.room.TypeConverter;

import com.example.dmitriy.emergencyassistant.model.user.UserRole;

public class ConverterUserRole {


    @TypeConverter
    public String fromUserRole(UserRole userRole){
        return userRole.name();
    }

    @TypeConverter
    public UserRole toUserRole(String data){
        return UserRole.valueOf(data);
    }
}
