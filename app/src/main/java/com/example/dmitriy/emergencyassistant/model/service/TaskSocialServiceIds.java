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

    @SerializedName("userHardupId")
    @Expose
    private Long uid;

    @SerializedName("sid")
    @Expose
    private Long sid;

    @SerializedName("userEmployeeId")
    @Expose
    private Long eid;

    public TaskSocialServiceIds (Long uid, Long eid, Long sid){
        this.uid = uid;
        this.sid = sid;
        this.eid = eid;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }
}
