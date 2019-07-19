/*
 *
 *  Created by Dmitry Garmyshev on 7/19/19 1:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/19/19 12:12 PM
 *
 */

package com.example.dmitriy.emergencyassistant.interfaces.volunteer;

import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;

public interface InterfaceVolunteerChangeFragments {

    void setMain();
    void setSettings();
    void setTasksList(User needy, String date);

}
