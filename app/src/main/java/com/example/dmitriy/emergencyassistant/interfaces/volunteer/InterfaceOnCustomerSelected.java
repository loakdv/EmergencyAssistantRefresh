/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 8:37 PM
 *
 */

package com.example.dmitriy.emergencyassistant.interfaces.volunteer;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;

public interface InterfaceOnCustomerSelected {

    void onCustomerClick(EntityVolunteerAddedNeedy needy, String date);

}
