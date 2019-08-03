/*
 *
 *  Created by Dmitry Garmyshev on 8/3/19 12:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/28/19 10:03 PM
 *
 */

package com.example.dmitriy.emergencyassistant.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskSocialServiceIds {

    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("sid")
    @Expose
    private Long sid;

    public TaskSocialServiceIds (String uid, Long sid){
        this.uid = uid;
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }
}
