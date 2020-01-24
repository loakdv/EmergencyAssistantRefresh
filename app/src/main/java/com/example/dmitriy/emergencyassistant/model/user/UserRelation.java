/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 3:16 PM
 *
 */

package com.example.dmitriy.emergencyassistant.model.user;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRelation {

    @SerializedName("user")
    @Expose
    private EntityUser user;

    @SerializedName("active")
    @Expose
    private boolean active;

    public UserRelation() {
    }

    public UserRelation(EntityUser user, boolean active) {
        this.user = user;
        this.active = active;
    }

    public EntityUser getUser() {
        return user;
    }

    public void setUser(EntityUser user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
