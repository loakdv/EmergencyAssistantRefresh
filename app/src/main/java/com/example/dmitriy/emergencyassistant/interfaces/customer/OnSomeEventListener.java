/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 10:10 AM
 *
 */

package com.example.dmitriy.emergencyassistant.interfaces.customer;

public interface OnSomeEventListener {
    //Методы которые должны выполниться внутри активности
    void changeFrag();
    void sendSos();
    void sendHelpSignal(int type);
    void checkState();
}
