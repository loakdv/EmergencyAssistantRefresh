/*
 *
 *  Created by Dmitry Garmyshev on 18.01.20 14:13
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 18.01.20 14:13
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.task;

import android.arch.persistence.room.TypeConverter;

import com.example.dmitriy.emergencyassistant.model.service.TaskStatus;

public class ConverterTaskStatus {

    @TypeConverter
    public String fromTaskStatus(TaskStatus taskStatus){
        return taskStatus.name();
    }

    @TypeConverter
    public TaskStatus toTaskStatus(String data){
        return TaskStatus.valueOf(data);
    }
}
