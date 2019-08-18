/*
 *
 *  Created by Dmitry Garmyshev on 8/18/19 10:33 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/17/19 11:44 AM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.dmitriy.emergencyassistant.helpers.HelperStrings;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.model.user.UserRelation;
import com.example.dmitriy.emergencyassistant.model.user.UserRole;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.organization.EntityOrganization;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.converter.ConverterUserRole;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.personal.EntityUserPersonal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Entity(indices = {@Index(value = "nickname", unique = true)})
public class EntityUser {

    @PrimaryKey
    @NonNull
    private String nickname;

    private String password;

    private String firstname;

    private String lastname;

    private String middlename;

    @TypeConverters({ConverterUserRole.class})
    private UserRole userRole;

    private boolean isActive;






    public static class Builder {

        //Обязательные поля
        private final String nickname;
        private final String password;

        //Необязательные параметры со значением по умолчанию
        private String firstname = HelperStrings.NOT_SPECIFIED;
        private String lastname = HelperStrings.NOT_SPECIFIED;
        private String middlename = HelperStrings.NOT_SPECIFIED;
        private UserRole userRole = UserRole.HARDUP;
        private boolean isActive = false;


        public Builder(String nickname, String password){
            this.nickname = nickname;
            this.password = password;
        }


        public Builder setFirstname(String firstname){
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public Builder setMiddlename(String middlename){
            this.middlename = middlename;
            return this;
        }

        public Builder setUserRole(UserRole userRole){
            this.userRole = userRole;
            return this;
        }

        public Builder setActive(boolean isActive){
            this.isActive = isActive;
            return this;
        }


        public EntityUser build(){
            return new EntityUser(this);
        }

    }

    public EntityUser(){}

    private EntityUser(Builder builder){
        this.nickname = builder.nickname;
        this.password = builder.password;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.middlename = builder.middlename;
        this.isActive = builder.isActive;
        this.userRole = builder.userRole;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public boolean isActive() {
        return isActive;
    }
}
