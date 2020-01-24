/*
 *
 *  Created by Dmitry Garmyshev on 18.01.20 14:51
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 13.12.19 21:15
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.dmitriy.emergencyassistant.helpers.HelperStrings;
import com.example.dmitriy.emergencyassistant.model.user.UserRole;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.converter.ConverterUserRole;

@Entity(indices = {@Index(value = "nickname", unique = true)})
public class EntityUser {

    @PrimaryKey
    @NonNull
    private String nickname;

    private Long id;

    private String password;

    private String firstname;

    private String lastname;

    private String middlename;

    private String address;

    private String phone;

    private String mobile;

    private String email;

    @TypeConverters({ConverterUserRole.class})
    private UserRole userRole;

    private boolean isActive;






    public static class Builder {

        //Обязательные поля
        private final String nickname;
        private final String password;

        //Необязательные параметры со значением по умолчанию
        private Long id = 0l;
        private String firstname = HelperStrings.NOT_SPECIFIED;
        private String lastname = HelperStrings.NOT_SPECIFIED;
        private String middlename = HelperStrings.NOT_SPECIFIED;

        private String address = HelperStrings.NOT_SPECIFIED;
        private String phone = HelperStrings.NOT_SPECIFIED;
        private String mobile = HelperStrings.NOT_SPECIFIED;
        private String email = HelperStrings.NOT_SPECIFIED;

        private UserRole userRole = UserRole.HARDUP;
        private boolean isActive = false;


        public Builder(String nickname, String password){
            this.nickname = nickname;
            this.password = password;
        }

        public Builder setId(Long id){
            if(id == null){
                this.id = 0l;
            }
            else this.id = id;

            return this;
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

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }


        public Builder setMobile(String mobile){
            this.mobile = mobile;
            return this;
        }

        public Builder setPhone(String phone){
            this.phone = phone;
            return this;
        }

        public Builder setAddress(String address){
            this.address = address;
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
        this.email = builder.email;
        this.mobile = builder.mobile;
        this.phone = builder.phone;
        this.address = builder.address;
        this.id = builder.id;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
