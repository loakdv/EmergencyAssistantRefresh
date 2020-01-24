/*
 *
 *  Created by Dmitry Garmyshev on 09.12.19 17:34
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 09.12.19 17:34
 *
 */

package com.example.dmitriy.emergencyassistant.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public enum TaskStatus {

    @SerializedName("NEW")
    @Expose
    NEW,

    @SerializedName("PROCESSING")
    @Expose
    PROCESSING,

    @SerializedName("PENDING")
    @Expose
    PENDING,

    @SerializedName("SOLVED")
    @Expose
    SOLVED,

    @SerializedName("CLOSED")
    @Expose
    CLOSED;

    TaskStatus(){}

}
