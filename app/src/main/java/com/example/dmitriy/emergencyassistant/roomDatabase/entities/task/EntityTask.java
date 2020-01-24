/*
 *
 *  Created by Dmitry Garmyshev on 17.01.20 11:55
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 17.01.20 11:55
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.task;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.example.dmitriy.emergencyassistant.model.service.TaskStatus;

import java.util.Date;

@Entity
public class EntityTask {

    @PrimaryKey
    private Long taskId;

    private Long taskOwnerId;

    private boolean enabled;

    private Date dateCreate;

    private String taskDescription;

    @TypeConverters({ConverterTaskStatus.class})
    private TaskStatus taskStatus;
}
