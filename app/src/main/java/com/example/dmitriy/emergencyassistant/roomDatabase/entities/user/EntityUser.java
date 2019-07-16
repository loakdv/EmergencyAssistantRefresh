/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 7:47 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.dmitriy.emergencyassistant.model.user.UserRelation;
import com.example.dmitriy.emergencyassistant.model.user.UserRole;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.organization.EntityOrganization;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.personal.EntityUserPersonal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;


public class EntityUser {


}
