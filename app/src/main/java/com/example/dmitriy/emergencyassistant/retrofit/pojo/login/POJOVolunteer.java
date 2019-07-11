/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.pojo.login;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJOVolunteer {

    @SerializedName("profile_id")
    @Expose
    public String profile_id;

    @SerializedName("organization")
    @Expose
    public String organization;

    public POJOVolunteer(String organization, String profile_id){
        this.profile_id=profile_id;
        this.organization=organization;
    }

    public POJOVolunteer(){}

    public String getProfile_id() {
        return this.profile_id;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
