/*
 *
 *  Created by Dmitry Garmyshev on 15.12.19 11:31
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 15.12.19 11:31
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class EntityPhoneNumber {

    @PrimaryKey (autoGenerate = true)
    public Long id;

    public Long userId;

    public String number;

    public String name;

    public EntityPhoneNumber(String name, String number, Long userId){
        this.userId = userId;
        this.name = name;
        this.number = number;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
