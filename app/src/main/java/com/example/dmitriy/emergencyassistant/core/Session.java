/*
 *
 *  Created by Dmitry Garmyshev on 15.12.19 11:39
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 15.12.19 11:39
 *
 */

package com.example.dmitriy.emergencyassistant.core;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;

public class Session {

    private EntityUser currentUser;

    public Session(EntityUser currentUser){
        this.currentUser = currentUser;
    }

    public EntityUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(EntityUser currentUser) {
        this.currentUser = currentUser;
    }
}
