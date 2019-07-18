/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/18/19 12:43 PM
 *
 */

package com.example.dmitriy.emergencyassistant.interfaces.volunteer;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;

public interface InterfaceVolunteerChangeFragments {

    void setMain();
    void setSettings();
    void setTasksList(EntityVolunteerAddedNeedy needy, String date);

}
