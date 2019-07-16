/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:32 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 8:30 PM
 *
 */

package com.example.dmitriy.emergencyassistant.interfaces.volunteer;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;

public interface InterfaceVolunteerChangeFragments {

    void setMain();
    void setSettings();
    void setTasks(EntityVolunteerAddedNeedy needy, String date);

}
